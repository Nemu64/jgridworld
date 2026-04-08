package com.nemu.jgridworld;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.zip.CRC32;

import static com.nemu.jgridworld.Shared.stm;

public class STM {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public static final String SIMULATION_RUNNING_PROPERTY = "simulationRunning";
    public static final String CONFIGURATION_LOADED_PROPERTY = "configurationLoaded";
    final int agent = 100, bigPrize = 50, smallPrize = 10, cliff = -100, wall = -999, moveUp = 101, moveDown = 102, moveLeft = 103, moveRight = 104, moveUpLeft = 105, moveDownLeft = 106, moveUpRight = 107, moveDownRight = 108;
    final int agentColDefault = 0, agentRowDefault = 4;
    final String[] moveNames = {"Up", "Down", "Left", "Right", "Up-Left", "Down-Left", "Up-Right", "Down-Right"};


    int numOfTrials = 100;
    int numOfMoves = 100;
    int outOfBoundsRule = 0;
    int simulationSpeed = 200;
    double defaultReward = 0.0;
    double defaultExplorationProb = 0.1;
    double defaultLearningRate = 0.1;
    double defaultDiscountFactor = 0.9;
    boolean explorationRateDecays = true;
    boolean kingsMoves = false;
    boolean useWind = false;
    int[] wind = {0, 0, 0, 1, 1, 1, 2, 2, 1, 0};
    int windDirection = 0;
    boolean showSimulationProgress = true;


    int agentRow = agentRowDefault;
    int agentCol = agentColDefault;
    long agentDeaths = 0;
    long totalExploratoryMoves = 0;
    long totalExploitativeMoves = 0;
    long numOfSuccesses = 0;
    long numOfSmallPrizeSuccesses = 0;
    boolean simulationRunning = false;
    boolean lastSimulationCompleted = false;
    volatile boolean simulationPaused = false;
    final Object pauseLock = new Object();


    double[][] gridWorld = new double[10][10];
    double[][] reward = new double[10][10];
    double[][] upValues = new double[10][10];
    double[][] downValues = new double[10][10];
    double[][] leftValues = new double[10][10];
    double[][] rightValues = new double[10][10];
    double[][] upLeftValues = new double[10][10];
    double[][] upRightValues = new double[10][10];
    double[][] downLeftValues = new double[10][10];
    double[][] downRightValues = new double[10][10];


    boolean isGridModified = false;
    int agentRowModified = agentRowDefault;
    int agentColModified = agentColDefault;
    double[][] modifiedGridWorld = new double[10][10];
    double[][] modifiedReward = new double[10][10];


    final Object reportLock = new Object();
    ArrayList<String> reports = new ArrayList<String>();
    Random random = new Random();
    ES es = new ES();

    private SimulationWorker simulationWorker = null;

    record Cell(int row, int col) {
    }


    public STM() {
        this.es.ESInit();
    }


    public void STMTick() {
        if (simulationWorker == null || simulationWorker.isDone()) {

            simulationWorker = new SimulationWorker();


            showSimulationProgress = Shared.preferenceManager.getPromptPreferredAction("rememberAskViewSimulation").isPresent() ? Shared.preferenceManager.getPromptPreferredAction("rememberAskViewSimulation").get() : Shared.promptFactory.setMessage("Do you want to watch the simulation?").setTitle("View Simulation").setDNAA(true)
                    .setPreferenceKey("rememberAskViewSimulation").setModal(true).setDefaultOption("yes").setTimeout(30).create()
                    .getAnswer();
            Shared.setShowSimulationCheckBoxMenuItem(showSimulationProgress);

            Shared.log("STM preparing to start simulation...", "info");
            Shared.setStatusBarText("Initializing simulation...");
            Shared.setStatusBarState("BUSY - DO NOT REBOOT!!");
            Shared.setTopBoxText("INITIALIZING SIMULATION...");
            Shared.toggleStartAbortButton();
            setSimulationRunning(true);
            simulationWorker.execute();
            if (!showSimulationProgress) Shared.setTopBoxText("RUNNING SIMULATION...");

        }
        else {

            es.ESPause();
            Shared.log("Waiting for confirmation of abort request...", "warning");
            GetYesOrNo prompt = new PromptFactory()
                    .setTitle("Abort Simulation")
                    .setMessage("Are you sure you want to abort the simulation?")
                    .setModal(true)
                    .create();

            if (prompt.getAnswer()) {
                Shared.log("Abort request acknowledged...", "info");
                if (simulationWorker != null) {

                    simulationWorker.cancel(true);
                }


            }
            else {
                Shared.log("Resuming simulation...", "info");
                es.ESResume();
            }
        }
    }


    public double[][] getTableValues(String tableName) {
        return es.getValueTable(tableName);
    }


    public void addReport(String report) {
        synchronized (reportLock) {
            reports.add(report);
            if (reports.size() > Shared.preferenceManager.getStatisticAsInt("statsTotalNumReportsAtOnce")) {
                Shared.preferenceManager.setStatistic("statsTotalNumReportsAtOnce", reports.size());
            }
        }
    }

    public void voidReport(int reportIndex, int reason) {
        synchronized (reportLock) {
            if (reportIndex < 0 || reportIndex >= reports.size()) {
                Shared.messageFactory.setTitle("Error").setMessage("Can't find report to void.").setModal(true).create();
            }
            reports.set(reportIndex, "VOIDED\n" + new String[]{"USER ERROR", "SYSTEM ERROR", "DATA DISCREPANCY"}[reason]);
            reports.trimToSize();
            Shared.preferenceManager.setStatistic("statsTotalNumReportsVoided", Shared.preferenceManager.getStatisticAsInt("statsTotalNumReportsVoided") + 1);
        }
    }

    public void clearReports() {
        synchronized (reportLock) {
            reports.clear();
            reports.trimToSize();
        }
    }

    public String getReport(int reportIndex) {
        synchronized (reportLock) {
            if (reportIndex < 0 || reportIndex >= reports.size()) {
                return "No report found";
            }
            return reports.get(reportIndex);
        }
    }

    public int getNumOfReports() {
        synchronized (reportLock) {
            reports.trimToSize();
            return reports.size();
        }
    }


    public void applyGridModifications(double[][] editorRewardMap, int editorAgentRow, int editorAgentCol, double[][] editorDisplayGrid) {


        this.modifiedReward = deepCopyArray(editorRewardMap);

        this.modifiedGridWorld = deepCopyArray(editorDisplayGrid);
        this.agentRowModified = editorAgentRow;
        this.agentColModified = editorAgentCol;
        this.isGridModified = true;
        Shared.log("Grid modifications applied to STM.", "info");
    }

    public void resetToDefaultGrid() {
        this.isGridModified = false;
        Shared.log("STM reset to default grid settings.", "info");
    }


    public void saveConfiguration(File file) throws IOException {
        if (file == null) {
            Shared.log("Save cancelled", "warning");
            return;
        }
        StringBuilder dataForChecksum = new StringBuilder();


        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8)))) {


            writeLine(writer, dataForChecksum, kingsMoves ? "KING" : "CARDINAL");


            writeLine(writer, dataForChecksum, getSpeedName(simulationSpeed));
            writeLine(writer, dataForChecksum, String.valueOf(outOfBoundsRule));
            writeLine(writer, dataForChecksum, String.valueOf(numOfTrials));
            writeLine(writer, dataForChecksum, String.valueOf(numOfMoves));
            writeLine(writer, dataForChecksum, "OFF");
            writeLine(writer, dataForChecksum, useWind ? "ON" : "OFF");
            if (useWind) {
                writeLine(writer, dataForChecksum, windDirection == 0 ? "UP" : "DOWN");
            }
            writeLine(writer, dataForChecksum, String.valueOf(defaultReward));
            writeLine(writer, dataForChecksum, String.valueOf(defaultExplorationProb));
            writeLine(writer, dataForChecksum, explorationRateDecays ? "YES" : "NO");
            writeLine(writer, dataForChecksum, String.valueOf(defaultLearningRate));
            writeLine(writer, dataForChecksum, String.valueOf(defaultDiscountFactor));


            writeLine(writer, dataForChecksum, String.valueOf(numOfSuccesses));
            writeLine(writer, dataForChecksum, String.valueOf(numOfSmallPrizeSuccesses));
            long totalMovesActual = totalExploratoryMoves + totalExploitativeMoves;
            writeLine(writer, dataForChecksum, String.valueOf(totalMovesActual));
            writeLine(writer, dataForChecksum, String.valueOf(totalExploratoryMoves));
            writeLine(writer, dataForChecksum, String.valueOf(totalExploitativeMoves));


            writeLine(writer, dataForChecksum, isGridModified ? "MODIFIED" : "DEFAULT");


            double[][] gridToSave = isGridModified ? modifiedGridWorld : gridWorld;
            double[][] rewardToSave = isGridModified ? modifiedReward : reward;


            write2DArray(writer, dataForChecksum, gridToSave);

            write2DArray(writer, dataForChecksum, rewardToSave);


            write2DArray(writer, dataForChecksum, upValues);
            write2DArray(writer, dataForChecksum, downValues);
            write2DArray(writer, dataForChecksum, leftValues);
            write2DArray(writer, dataForChecksum, rightValues);
            if (kingsMoves) {
                write2DArray(writer, dataForChecksum, upLeftValues);
                write2DArray(writer, dataForChecksum, downLeftValues);
                write2DArray(writer, dataForChecksum, upRightValues);
                write2DArray(writer, dataForChecksum, downRightValues);
            }


            if (reports.isEmpty()) {
                writeLine(writer, dataForChecksum, "NO REPORTS");
            }
            else {
                writeLine(writer, dataForChecksum, String.format("START REPORTS (%d TOTAL)", reports.size()));
                for (int i = 0; i < reports.size(); i++) {
                    writeLine(writer, dataForChecksum, String.format("START REPORT %d", i + 1));

                    String[] reportLines = reports.get(i).split("\\R");
                    for (String reportLine : reportLines) {
                        writeLine(writer, dataForChecksum, reportLine);
                    }
                    writeLine(writer, dataForChecksum, String.format("END REPORT %d", i + 1));
                }
                writeLine(writer, dataForChecksum, "END REPORTS");
            }


            CRC32 crc = new CRC32();
            crc.update(dataForChecksum.toString().getBytes(StandardCharsets.UTF_8));
            String checksum = String.format("%08X", crc.getValue());
            writer.println(checksum);
            Shared.log("Configuration saved successfully", "info");
            Shared.messageFactory.setTitle("Configuration Saved").setMessage("Configuration saved successfully!").setModal(true).create();
            Shared.preferenceManager.setStatistic("statsTotalNumConfigsSaved", Shared.preferenceManager.getStatisticAsInt("statsTotalNumConfigsSaved") + 1);

        }
    }

    public void loadConfiguration(File file) throws IOException, IndexOutOfBoundsException, IllegalArgumentException {
        loadConfiguration(file, false);
    }

    public void loadConfiguration(File file, boolean silent) throws IOException, IndexOutOfBoundsException, IllegalArgumentException {
        if (file == null) {
            Shared.log("Load cancelled", "warning");
            return;
        }
        java.util.List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        if (lines.isEmpty()) {
            throw new IOException("Save file is empty.");
        }


        String expectedChecksum = lines.getLast();
        StringBuilder dataForChecksum = new StringBuilder();


        for (int i = 0; i < lines.size() - 1; i++) {
            dataForChecksum.append(lines.get(i));
        }

        CRC32 crc = new CRC32();
        crc.update(dataForChecksum.toString().getBytes(StandardCharsets.UTF_8));
        String calculatedChecksum = String.format("%08X", crc.getValue());

        if (!calculatedChecksum.equalsIgnoreCase(expectedChecksum)) {

            if (Shared.preferenceManager.getPreferenceAsBoolean("fileIgnoreBadChecksums")) {
                Shared.log("Checksum mismatch ignored", "warning");
                if (!silent) Shared.messageFactory.setTitle("Checksum Warning").setMessage("Checksum mismatch!<br>Chances that this file works are not high.").setModal(true).create();
            }
            else {
                Shared.messageFactory.setTitle("Checksum Error").setMessage("Checksum mismatch!<br>This file cannot be loaded.").setModal(true).create();
                if (!silent) Shared.log("Checksum mismatch! File may be corrupted.", "error");
                return;
            }
        }


        int lineIndex = 0;
        try {
            boolean loadedKingsMoves = "KING".equalsIgnoreCase(lines.get(lineIndex++));
            String speedName = lines.get(lineIndex++);
            int loadedSpeed = parseSpeedName(speedName);
            int loadedOutOfBoundsRule = Integer.parseInt(lines.get(lineIndex++));
            long loadedNumOfTrials = Math.clamp(Long.parseLong(lines.get(lineIndex++)), 1, 1000000);
            int loadedNumOfMoves = Math.clamp(Integer.parseInt(lines.get(lineIndex++)), 1, 999);
            boolean loadedUseSmallPrize = "ON".equalsIgnoreCase(lines.get(lineIndex++));
            boolean loadedUseWind = "ON".equalsIgnoreCase(lines.get(lineIndex++));
            int loadedWindDirection = 0;
            if (loadedUseWind) {
                loadedWindDirection = "UP".equalsIgnoreCase(lines.get(lineIndex++)) ? 0 : 1;
            }
            double loadedDefaultReward = Math.clamp(Double.parseDouble(lines.get(lineIndex++)), -99, 99);
            double loadedExplorationProb = Math.clamp(Double.parseDouble(lines.get(lineIndex++)), 0, 1);
            boolean loadedExplorationDecays = "YES".equalsIgnoreCase(lines.get(lineIndex++));
            double loadedLearningRate = Math.clamp(Double.parseDouble(lines.get(lineIndex++)), 0, 1);
            double loadedDiscountFactor = Math.clamp(Double.parseDouble(lines.get(lineIndex++)), 0, 1);


            int loadedNumSuccesses = Integer.parseInt(lines.get(lineIndex++));
            int loadedSmallPrizeSuccesses = Integer.parseInt(lines.get(lineIndex++));
            int loadedTotalMoves = Integer.parseInt(lines.get(lineIndex++));
            int loadedExploratoryMoves = Integer.parseInt(lines.get(lineIndex++));
            int loadedExploitativeMoves = Integer.parseInt(lines.get(lineIndex++));


            boolean loadedIsGridModified = "MODIFIED".equalsIgnoreCase(lines.get(lineIndex++));
            double[][] loadedGridWorld = parse2DArray(lines, lineIndex, 10);
            lineIndex += 10;
            double[][] loadedReward = parse2DArray(lines, lineIndex, 10);
            lineIndex += 10;


            double[][] loadedUpValues = parse2DArray(lines, lineIndex, 10);
            lineIndex += 10;
            double[][] loadedDownValues = parse2DArray(lines, lineIndex, 10);
            lineIndex += 10;
            double[][] loadedLeftValues = parse2DArray(lines, lineIndex, 10);
            lineIndex += 10;
            double[][] loadedRightValues = parse2DArray(lines, lineIndex, 10);
            lineIndex += 10;
            double[][] loadedUpLeftValues = new double[10][10];
            double[][] loadedDownLeftValues = new double[10][10];
            double[][] loadedUpRightValues = new double[10][10];
            double[][] loadedDownRightValues = new double[10][10];
            if (loadedKingsMoves) {
                loadedUpLeftValues = parse2DArray(lines, lineIndex, 10);
                lineIndex += 10;
                loadedDownLeftValues = parse2DArray(lines, lineIndex, 10);
                lineIndex += 10;
                loadedUpRightValues = parse2DArray(lines, lineIndex, 10);
                lineIndex += 10;
                loadedDownRightValues = parse2DArray(lines, lineIndex, 10);
                lineIndex += 10;
            }


            ArrayList<String> loadedReports = new ArrayList<>();
            if (lineIndex < lines.size() - 1 && lines.get(lineIndex).startsWith("START REPORTS")) {
                lineIndex++;
                while (lineIndex < lines.size() - 1 && !"END REPORTS".equalsIgnoreCase(lines.get(lineIndex))) {
                    if (lines.get(lineIndex).startsWith("START REPORT")) {
                        lineIndex++;
                        StringBuilder currentReportContent = new StringBuilder();
                        while (lineIndex < lines.size() - 1 && !lines.get(lineIndex).startsWith("END REPORT")) {
                            if (!currentReportContent.isEmpty()) {
                                currentReportContent.append(System.lineSeparator());
                            }
                            currentReportContent.append(lines.get(lineIndex++));
                        }
                        loadedReports.add(currentReportContent.toString());
                        if (lineIndex < lines.size() - 1 && lines.get(lineIndex).startsWith("END REPORT")) {
                            lineIndex++;
                        }
                    }
                    else {
                        throw new IOException("Invalid report format found at line " + (lineIndex + 1));
                    }
                }
                if (lineIndex < lines.size() - 1 && "END REPORTS".equalsIgnoreCase(lines.get(lineIndex))) {
                    lineIndex++;
                }
            }
            else if (lineIndex < lines.size() - 1 && "NO REPORTS".equalsIgnoreCase(lines.get(lineIndex))) {
                lineIndex++;
            }


            this.kingsMoves = loadedKingsMoves;
            this.simulationSpeed = loadedSpeed;
            this.outOfBoundsRule = loadedOutOfBoundsRule;
            this.numOfTrials = (int) loadedNumOfTrials;
            this.numOfMoves = loadedNumOfMoves;

            this.useWind = loadedUseWind;
            this.windDirection = loadedWindDirection;
            this.defaultReward = loadedDefaultReward;
            this.defaultExplorationProb = loadedExplorationProb;
            this.explorationRateDecays = loadedExplorationDecays;
            this.defaultLearningRate = loadedLearningRate;
            this.defaultDiscountFactor = loadedDiscountFactor;


            this.numOfSuccesses = 0;
            this.numOfSmallPrizeSuccesses = 0;


            this.totalExploratoryMoves = 0;
            this.totalExploitativeMoves = 0;

            this.isGridModified = loadedIsGridModified;


            this.modifiedGridWorld = loadedGridWorld;
            this.modifiedReward = loadedReward;

            if (this.isGridModified) {
                this.reward = deepCopyArray(this.modifiedReward);

                this.agentRowModified = findAgentRow(this.modifiedGridWorld);
                this.agentColModified = findAgentCol(this.modifiedGridWorld);
                this.agentRow = this.agentRowModified;
                this.agentCol = this.agentColModified;
            }
            else {
                this.reward = createDefaultRewardMap();
                this.agentRow = this.agentRowDefault;
                this.agentCol = this.agentColDefault;
            }

            this.gridWorld = createDisplayGridFromRewards(this.reward, this.agentRow, this.agentCol);


            this.upValues = loadedUpValues;
            this.downValues = loadedDownValues;
            this.leftValues = loadedLeftValues;
            this.rightValues = loadedRightValues;
            this.upLeftValues = loadedUpLeftValues;
            this.downLeftValues = loadedDownLeftValues;
            this.upRightValues = loadedUpRightValues;
            this.downRightValues = loadedDownRightValues;


            this.reports = loadedReports;
            Shared.setCurrentReportNumber(loadedReports.size() - 1);
            if (!reports.isEmpty()) {
                Shared.setReportText(reports.getLast());
            }
            else {
                Shared.setReportText("No reports to show");
            }
            Shared.log("Configuration loaded successfully from " + file.getName(), "info");
            if (!silent) Shared.messageFactory.setTitle("Configuration Loaded").setMessage("Configuration loaded successfully!").setModal(true).create();
            Shared.updateAllTables();
            setConfigurationLoaded();
            Shared.preferenceManager.setStatistic("statsTotalNumConfigsLoaded", Shared.preferenceManager.getStatisticAsInt("statsTotalNumConfigsLoaded") + 1);

        }
        catch (Exception e) {
            Shared.log("Error loading file: " + e.getMessage(), "error");

            throw new IOException("Failed to parse save file: " + e.getMessage(), e);
        }
    }


    private String getSpeedName(int speedValue) {

        return switch (speedValue) {
            case 1000 -> "MANUAL";
            case 500 -> "SLOW";
            case 200 -> "MEDIUM";
            case 100 -> "FAST";
            case 1 -> "FASTER THAN LIGHT";
            default -> "MEDIUM";
        };
    }

    private int parseSpeedName(String name) {
        return switch (name.toUpperCase()) {
            case "MANUAL" -> 1000;
            case "SLOW" -> 500;
            case "MEDIUM" -> 200;
            case "FAST" -> 100;
            case "FASTER THAN LIGHT" -> 1;
            default -> 200;
        };
    }

    public double[][] deepCopyArray(double[][] source) {
        if (source == null) {

            return new double[10][10];
        }

        double[][] destination = new double[source.length][];
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                destination[i] = Arrays.copyOf(source[i], source[i].length);
            }
            else {

                destination[i] = new double[10];
            }
        }
        return destination;
    }

    private double[][] createDefaultRewardMap() {
        double[][] defaultArr = new double[10][10];
        for (int r = 0; r < 10; r++) {

            Arrays.fill(defaultArr[r], defaultReward);
        }

        if (defaultArr[agentRowDefault][agentColDefault] == defaultReward) {
            defaultArr[agentRowDefault][agentColDefault] = 0;
        }
        defaultArr[4][9] = bigPrize;


        return defaultArr;
    }

    private int findAgentRow(double[][] grid) {

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == agent) {
                    return r;
                }
            }
        }
        return agentRowDefault;
    }

    private int findAgentCol(double[][] grid) {

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == agent) {
                    return c;
                }
            }
        }
        return agentColDefault;
    }

    private double[][] createDisplayGridFromRewards(double[][] rewardMap, int agentR, int agentC) {
        double[][] displayGrid = new double[10][10];
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (rewardMap[r][c] == wall || rewardMap[r][c] == cliff ||
                        rewardMap[r][c] == bigPrize || rewardMap[r][c] == smallPrize) {
                    displayGrid[r][c] = rewardMap[r][c];
                }
            }
        }
        if (agentR >= 0 && agentR < 10 && agentC >= 0 && agentC < 10) {
            displayGrid[agentR][agentC] = agent;
        }
        return displayGrid;
    }

    private void writeLine(PrintWriter writer, StringBuilder checksumData, String line) {
        writer.println(line);
        checksumData.append(line);
    }

    private void write2DArray(PrintWriter writer, StringBuilder checksumData, double[][] array) {
        for (int i = 0; i < array.length; i++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int j = 0; j < array[i].length; j++) {
                rowBuilder.append(array[i][j]);
                if (j < array[i].length - 1) {
                    rowBuilder.append(",");
                }
            }
            writeLine(writer, checksumData, rowBuilder.toString());
        }
    }

    private double[][] parse2DArray(List<String> lines, int startIndex, int numRows) throws NumberFormatException, IndexOutOfBoundsException {
        double[][] array = new double[numRows][10];
        for (int i = 0; i < numRows; i++) {
            String[] values = lines.get(startIndex + i).split(",");
            if (values.length != 10) {
                throw new NumberFormatException("Incorrect number of columns in array data at line " + (startIndex + i + 1));
            }
            for (int j = 0; j < 10; j++) {
                array[i][j] = Math.clamp(Double.parseDouble(values[j]), -999, 999);


            }
        }
        return array;
    }

    public boolean isPathToPrizeAvailable(double[][] currentRewardGrid, int startRow, int startCol) {
        int rows = 10;
        int cols = 10;


        List<STM.Cell> prizeLocations = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (currentRewardGrid[r][c] == stm.bigPrize) {

                    prizeLocations.add(new STM.Cell(r, c));
                }
            }
        }
        if (prizeLocations.isEmpty()) {
            return true;
        }

        boolean[][] visited = new boolean[rows][cols];

        Queue<STM.Cell> queue = new LinkedList<>();

        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols ||
                currentRewardGrid[startRow][startCol] == stm.wall ||
                currentRewardGrid[startRow][startCol] == stm.cliff) {
            return false;
        }


        queue.offer(new STM.Cell(startRow, startCol));
        visited[startRow][startCol] = true;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            STM.Cell current = queue.poll();


            if (currentRewardGrid[current.row()][current.col()] == stm.bigPrize ||
                    currentRewardGrid[current.row()][current.col()] == stm.smallPrize) {
                return true;
            }


            for (int i = 0; i < 4; i++) {


                int nextRow = current.row() + dRow[i];
                int nextCol = current.col() + dCol[i];


                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {

                    if (!visited[nextRow][nextCol] &&
                            currentRewardGrid[nextRow][nextCol] != stm.wall &&
                            currentRewardGrid[nextRow][nextCol] != stm.cliff) {
                        visited[nextRow][nextCol] = true;

                        queue.offer(new STM.Cell(nextRow, nextCol));
                    }
                }
            }
        }
        return false;
    }

    private boolean areValuesBlank() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (upValues[i][j] != 0 || downValues[i][j] != 0 || leftValues[i][j] != 0 || rightValues[i][j] != 0 || upLeftValues[i][j] != 0 || upRightValues[i][j] != 0 || downLeftValues[i][j] != 0 || downRightValues[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    private void setSimulationRunning(boolean running) {
        boolean oldState = simulationRunning;
        simulationRunning = running;

        if (oldState != running) {

            SwingUtilities.invokeLater(() -> {
                        pcs.firePropertyChange(SIMULATION_RUNNING_PROPERTY, oldState, simulationRunning);
                    }
            );


        }
    }

    private void setConfigurationLoaded() {
        pcs.firePropertyChange(CONFIGURATION_LOADED_PROPERTY, false, true);
    }


    class ES {


        public void ESInit() {
            simulationPaused = false;
            agentDeaths = 0;
            numOfSuccesses = 0;
            numOfSmallPrizeSuccesses = 0;
            totalExploratoryMoves = 0;
            totalExploitativeMoves = 0;


            if (!areValuesBlank()) {
                if (Shared.preferenceManager.getPromptPreferredAction("rememberAskUsePreviousValues").isPresent()) {
                    if (Shared.preferenceManager.getPromptPreferredAction("rememberAskUsePreviousValues").get()) {
                        return;
                    }
                }
                else if (Shared.promptFactory.setTitle("Use Previous Values").setMessage("Do you want to use the values from the previous simulation?").setDNAA(true).setPreferenceKey("rememberAskUsePreviousValues").setModal(true).create().getAnswer()) {
                    return;
                }
            }


            upValues = new double[10][10];
            downValues = new double[10][10];
            leftValues = new double[10][10];
            rightValues = new double[10][10];
            upLeftValues = new double[10][10];
            downLeftValues = new double[10][10];
            upRightValues = new double[10][10];
            downRightValues = new double[10][10];

        }


        public int ESChooseAction(int trial) {
            int moveType;
            int oldRow = agentRow, oldCol = agentCol;
            final double[] moveValues = getMoveValues();


            boolean allEqual = Arrays.stream(moveValues).allMatch(value -> value == Arrays.stream(moveValues).max().getAsDouble());
            boolean shouldExplore = random.nextDouble() < (explorationRateDecays ? defaultExplorationProb / Math.sqrt(trial + 1) : defaultExplorationProb);
            int move;

            if (allEqual || shouldExplore) {

                move = random.nextInt(kingsMoves ? 8 : 4) + 101;
                totalExploratoryMoves++;
                moveType = 0;
            }
            else {

                move = 101;
                double maxValue = moveValues[0];
                for (int i = 1; i < moveValues.length; i++) {
                    if (moveValues[i] > maxValue) {
                        maxValue = moveValues[i];
                        move = i + 101;
                    }
                }
                totalExploitativeMoves++;
                moveType = 1;
            }


            int tempAgentRow = agentRow;
            int tempAgentCol = agentCol;
            if (showSimulationProgress) Shared.setTopBoxText((allEqual ? "No best move. " : shouldExplore ? "Exploring. " : "") + "Moving " + moveNames[move - 101] + ".");
            switch (move) {
                case moveUp:
                    tempAgentRow--;
                    break;
                case moveDown:
                    tempAgentRow++;
                    break;
                case moveLeft:
                    tempAgentCol--;
                    break;
                case moveRight:
                    tempAgentCol++;
                    break;
                case moveUpLeft:
                    tempAgentRow--;
                    tempAgentCol--;
                    break;
                case moveDownLeft:
                    tempAgentRow++;
                    tempAgentCol--;
                    break;
                case moveUpRight:
                    tempAgentRow--;
                    tempAgentCol++;
                    break;
                case moveDownRight:
                    tempAgentRow++;
                    tempAgentCol++;
                    break;
                default:
                    throw new IllegalStateException("Invalid move selected: " + move);
            }


            if (tempAgentRow < 0 || tempAgentRow > 9 || tempAgentCol < 0 || tempAgentCol > 9) {


                switch (outOfBoundsRule) {
                    case 0 -> {
                        return -1;
                    }
                    case 1 -> {
                        return -2;
                    }
                    case 2 -> {
                        return -3;
                    }
                    case 3 -> {

                        if (tempAgentRow < 0) {
                            tempAgentRow = 9;
                        }
                        else if (tempAgentRow > 9) tempAgentRow = 0;
                        if (tempAgentCol < 0) {
                            tempAgentCol = 9;
                        }
                        else if (tempAgentCol > 9) tempAgentCol = 0;

                        if (reward[tempAgentRow][tempAgentCol] == wall) {

                            tempAgentRow = oldRow;
                            tempAgentCol = oldCol;

                            if (moveType == 0) {
                                totalExploratoryMoves--;
                            }
                            else {
                                totalExploitativeMoves--;
                            }
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected OOB value: " + outOfBoundsRule);
                }


            }
            else if (reward[tempAgentRow][tempAgentCol] == wall) {

                tempAgentRow = oldRow;
                tempAgentCol = oldCol;

                if (moveType == 0) {
                    totalExploratoryMoves--;
                }
                else {
                    totalExploitativeMoves--;
                }
            }


            if (move >= moveUpLeft) {
                try {
                    boolean clip = switch (move) {
                        case moveUpLeft -> reward[oldRow - 1][oldCol] == wall || reward[oldRow][oldCol - 1] == wall;
                        case moveDownLeft -> reward[oldRow + 1][oldCol] == wall || reward[oldRow][oldCol - 1] == wall;
                        case moveUpRight -> reward[oldRow - 1][oldCol] == wall || reward[oldRow][oldCol + 1] == wall;
                        case moveDownRight -> reward[oldRow + 1][oldCol] == wall || reward[oldRow][oldCol + 1] == wall;
                        default -> false;
                    };
                    if (clip) {
                        return -1;
                    }
                }
                catch (IndexOutOfBoundsException ignored) {
                    return -1;
                }
            }


            if (useWind && wind[tempAgentCol] > 0) {
                int windMove = wind[tempAgentCol];
                for (int w = 0; w < windMove; w++) {
                    int nextRow = (windDirection == 0) ? tempAgentRow - 1 : tempAgentRow + 1;
                    if (nextRow >= 0 && nextRow <= 9 && reward[nextRow][tempAgentCol] != wall) {
                        tempAgentRow = nextRow;
                    }
                    else {
                        break;
                    }
                }
            }


            agentRow = tempAgentRow;
            agentCol = tempAgentCol;


            double newStateValue = getNewStateValue(new int[]{agentRow, agentCol});

            double rewardReceived = reward[agentRow][agentCol];


            switch (move) {
                case moveUp:
                    upValues[oldRow][oldCol] = Math.clamp(upValues[oldRow][oldCol] + defaultLearningRate * (rewardReceived + defaultDiscountFactor * newStateValue - upValues[oldRow][oldCol]), -99, 99);
                    break;
                case moveDown:
                    downValues[oldRow][oldCol] = Math.clamp(downValues[oldRow][oldCol] + defaultLearningRate * (rewardReceived + defaultDiscountFactor * newStateValue - downValues[oldRow][oldCol]), -99, 99);
                    break;
                case moveLeft:
                    leftValues[oldRow][oldCol] = Math.clamp(leftValues[oldRow][oldCol] + defaultLearningRate * (rewardReceived + defaultDiscountFactor * newStateValue - leftValues[oldRow][oldCol]), -99, 99);
                    break;
                case moveRight:
                    rightValues[oldRow][oldCol] = Math.clamp(rightValues[oldRow][oldCol] + defaultLearningRate * (rewardReceived + defaultDiscountFactor * newStateValue - rightValues[oldRow][oldCol]), -99, 99);
                    break;
                case moveUpLeft:
                    upLeftValues[oldRow][oldCol] = Math.clamp(upLeftValues[oldRow][oldCol] + defaultLearningRate * (rewardReceived + defaultDiscountFactor * newStateValue - upLeftValues[oldRow][oldCol]), -99, 99);
                    break;
                case moveDownLeft:
                    downLeftValues[oldRow][oldCol] = Math.clamp(downLeftValues[oldRow][oldCol] + defaultLearningRate * (rewardReceived + defaultDiscountFactor * newStateValue - downLeftValues[oldRow][oldCol]), -99, 99);
                    break;
                case moveUpRight:
                    upRightValues[oldRow][oldCol] = Math.clamp(upRightValues[oldRow][oldCol] + defaultLearningRate * (rewardReceived + defaultDiscountFactor * newStateValue - upRightValues[oldRow][oldCol]), -99, 99);
                    break;
                case moveDownRight:
                    downRightValues[oldRow][oldCol] = Math.clamp(downRightValues[oldRow][oldCol] + defaultLearningRate * (rewardReceived + defaultDiscountFactor * newStateValue - downRightValues[oldRow][oldCol]), -99, 99);
                    break;
            }


            if (agentRow != oldRow || agentCol != oldCol) {
                gridWorld[agentRow][agentCol] = agent;


                if (reward[oldRow][oldCol] != wall && reward[oldRow][oldCol] != cliff &&
                        reward[oldRow][oldCol] != bigPrize && reward[oldRow][oldCol] != smallPrize) {
                    gridWorld[oldRow][oldCol] = move;
                }
            }

            return move;
        }


        private double getNewStateValue(int[] newState) {
            double maxQ = Double.NEGATIVE_INFINITY;

            maxQ = Math.max(maxQ, upValues[newState[0]][newState[1]]);
            maxQ = Math.max(maxQ, downValues[newState[0]][newState[1]]);
            maxQ = Math.max(maxQ, leftValues[newState[0]][newState[1]]);
            maxQ = Math.max(maxQ, rightValues[newState[0]][newState[1]]);

            if (kingsMoves) {
                maxQ = Math.max(maxQ, upLeftValues[newState[0]][newState[1]]);
                maxQ = Math.max(maxQ, upRightValues[newState[0]][newState[1]]);
                maxQ = Math.max(maxQ, downLeftValues[newState[0]][newState[1]]);
                maxQ = Math.max(maxQ, downRightValues[newState[0]][newState[1]]);
            }


            return maxQ == Double.NEGATIVE_INFINITY ? 0.0 : maxQ;
        }


        private double[] getMoveValues() {
            double[] moveValues;
            if (kingsMoves) {
                moveValues = new double[]{
                        upValues[agentRow][agentCol],
                        downValues[agentRow][agentCol],
                        leftValues[agentRow][agentCol],
                        rightValues[agentRow][agentCol],
                        upLeftValues[agentRow][agentCol],
                        downLeftValues[agentRow][agentCol],
                        upRightValues[agentRow][agentCol],
                        downRightValues[agentRow][agentCol]
                };
            }
            else {
                moveValues = new double[]{
                        upValues[agentRow][agentCol],
                        downValues[agentRow][agentCol],
                        leftValues[agentRow][agentCol],
                        rightValues[agentRow][agentCol]
                };
            }
            return moveValues;
        }


        public void ESPause() {
            synchronized (pauseLock) {
                Shared.stopWindowShake();
                simulationPaused = true;
                Shared.log("Simulation paused...", "info");
            }
        }

        public void ESResume() {
            synchronized (pauseLock) {
                if (simulationSpeed == 1 && showSimulationProgress) Shared.startWindowShake();
                simulationPaused = false;
                pauseLock.notify();
            }
        }


        public double[][] getValueTable(String tableName) {
            if (tableName == null || tableName.isEmpty()) {
                throw new IllegalArgumentException("Table name cannot be null or empty");
            }
            return switch (tableName) {
                case "gridWorld" -> Arrays.stream(gridWorld).map(double[]::clone).toArray(double[][]::new);
                case "averageValues" -> {
                    double[][] averageValues = new double[10][10];
                    double averageValue;
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            averageValue = (upValues[i][j] + downValues[i][j] + leftValues[i][j] + rightValues[i][j]) / 4;
                            if (kingsMoves) {
                                averageValue += (upLeftValues[i][j] + downLeftValues[i][j] + upRightValues[i][j] + downRightValues[i][j]) / 4;
                            }
                            averageValues[i][j] = averageValue;
                        }
                    }
                    yield averageValues;
                }
                case "upValues" -> Arrays.stream(upValues).map(double[]::clone).toArray(double[][]::new);
                case "downValues" -> Arrays.stream(downValues).map(double[]::clone).toArray(double[][]::new);
                case "leftValues" -> Arrays.stream(leftValues).map(double[]::clone).toArray(double[][]::new);
                case "rightValues" -> Arrays.stream(rightValues).map(double[]::clone).toArray(double[][]::new);
                case "upLeftValues" -> Arrays.stream(upLeftValues).map(double[]::clone).toArray(double[][]::new);
                case "downLeftValues" -> Arrays.stream(downLeftValues).map(double[]::clone).toArray(double[][]::new);
                case "upRightValues" -> Arrays.stream(upRightValues).map(double[]::clone).toArray(double[][]::new);
                case "downRightValues" -> Arrays.stream(downRightValues).map(double[]::clone).toArray(double[][]::new);
                default -> throw new IllegalArgumentException("STOP: Invalid table name: " + tableName);
            };
        }

    }


    private record SimulationUpdateData(int trial, int move, String statusText) {
    }


    private class SimulationWorker extends SwingWorker<Void, SimulationUpdateData> {

        @Override
        protected Void doInBackground() throws Exception {
            Thread.currentThread().setName("Simulation Thread");
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            Shared.preventShutdown("Simulation in progress...");

            Shared.log("Starting simulation...", "info");
            Shared.log("Running " + numOfTrials + " trials with " + numOfMoves + " moves each.", "info");
            Shared.preferenceManager.setStatistic("statsTotalNumSimulations", Shared.preferenceManager.getStatisticAsInt("statsTotalNumSimulations") + 1);
            es.ESInit();
            final boolean useModifiedSettings = isGridModified;
            final int startRow = useModifiedSettings ? agentRowModified : agentRowDefault;
            final int startCol = useModifiedSettings ? agentColModified : agentColDefault;
            Shared.log("Building reward table...");
            final double[][] sourceRewardMap = useModifiedSettings ? deepCopyArray(modifiedReward) : createDefaultRewardMap();

            Shared.log("Performing sanity checks...", "info");
            if (!isPathToPrizeAvailable(sourceRewardMap, startRow, startCol)) {
                Shared.log("No path to prize available. Simulation cannot proceed.", "error");
                this.cancel(true);
            }
            Shared.log("Go, go, go! The simulation is now running!", "info");
            for (int i = 0; i < numOfTrials && !isCancelled(); i++) {
                if (simulationSpeed == 1 && showSimulationProgress && !Shared.isWindowShaking()) Shared.startWindowShake();


                agentRow = startRow;
                agentCol = startCol;
                reward = deepCopyArray(sourceRewardMap);
                gridWorld = createDisplayGridFromRewards(reward, agentRow, agentCol);


                if (showSimulationProgress) publish(new SimulationUpdateData(i, 0, "Starting Trial " + (i + 1)));


                for (int j = 0; j < numOfMoves && !isCancelled(); j++) {


                    synchronized (pauseLock) {
                        if (simulationPaused && !isCancelled()) {
                            pauseLock.wait();
                        }
                    }
                    if (isCancelled()) break;


                    int actionCode;
                    do {
                        actionCode = es.ESChooseAction(i);
                        if (isCancelled()) break;
                    }
                    while (actionCode == -1 && !isCancelled());

                    if (isCancelled()) break;


                    if (actionCode == -2) {
                        Shared.log("Trial " + (i + 1) + " ended due to OOB rule.", "warning");
                        agentDeaths++;
                        break;
                    }
                    if (actionCode == -3) {
                        continue;
                    }


                    String status = "Running simulation... Trial " + (i + 1) + "/" + numOfTrials + " | Move " + (j + 1) + "/" + numOfMoves;
                    publish(new SimulationUpdateData(i, j + 1, status));


                    if (reward[agentRow][agentCol] == bigPrize) {
                        numOfSuccesses++;

                        break;
                    }
                    if (reward[agentRow][agentCol] == smallPrize) {
                        numOfSuccesses++;
                        numOfSmallPrizeSuccesses++;

                        break;
                    }

                    if (reward[agentRow][agentCol] == cliff) {
                        agentDeaths++;
                        break;
                    }


                    if (showSimulationProgress) {
                        Thread.sleep(simulationSpeed);
                    }

                }
            }


            if (!isCancelled()) {
                lastSimulationCompleted = true;
            }

            return null;
        }

        @Override
        protected void process(List<SimulationUpdateData> chunks) {

            if (chunks.isEmpty()) return;


            SimulationUpdateData latestUpdate = chunks.getLast();

            Shared.setStatusBarText(latestUpdate.statusText());
            if (showSimulationProgress) {
                Shared.updateTable();
            }

        }

        @Override
        protected void done() {

            try {
                get();

                Shared.stopWindowShake();

                if (!isCancelled()) {

                    long totalMovesActual = totalExploratoryMoves + totalExploitativeMoves;
                    Shared.log("Writing statistics...", "info");
                    Shared.preferenceManager.setStatistic("statsTotalNumCompletedSimulations", Shared.preferenceManager.getStatisticAsLong("statsTotalNumCompletedSimulations") + 1);
                    Shared.preferenceManager.setStatistic("statsTotalNumTrials", Shared.preferenceManager.getStatisticAsLong("statsTotalNumTrials") + numOfTrials);
                    Shared.preferenceManager.setStatistic("statsTotalNumSuccesses", Shared.preferenceManager.getStatisticAsLong("statsTotalNumSuccesses") + numOfSuccesses);
                    Shared.preferenceManager.setStatistic("statsTotalNumMoves", Shared.preferenceManager.getStatisticAsLong("statsTotalNumMoves") + totalExploratoryMoves + totalExploitativeMoves);
                    Shared.preferenceManager.setStatistic("statsTotalNumExploratoryMoves", Shared.preferenceManager.getStatisticAsLong("statsTotalNumExploratoryMoves") + totalExploratoryMoves);
                    Shared.preferenceManager.setStatistic("statsTotalNumExploitativeMoves", Shared.preferenceManager.getStatisticAsLong("statsTotalNumExploitativeMoves") + totalExploitativeMoves);
                    Shared.preferenceManager.setStatistic("statsTotalNumAgentDeaths", Shared.preferenceManager.getStatisticAsLong("statsTotalNumAgentDeaths") + agentDeaths);
                    Shared.log("Simulation completed!", "info");
                    Shared.log("We had " + (numOfSuccesses + numOfSmallPrizeSuccesses) + " successes out of " + numOfTrials + " trials.", "info");
                    Shared.setStatusBarText("Simulation completed!");


                    addReport(String.format(
                            "Total Trials: %,d\nSuccessful Trials: %,d\nSmall Prize Successes: %,d\nSuccess Rate: %.2f%%\nTotal Moves: %,d\nExploratory Moves: %,d\nExploitative Moves: %,d\nAverage Moves Per Trial: %.2f\nAverage Exploratory Moves: %.2f\nAverage Exploitative Moves: %.2f",
                            numOfTrials,
                            numOfSuccesses,
                            numOfSmallPrizeSuccesses,
                            ((double) numOfSuccesses / numOfTrials) * 100.0,
                            totalMovesActual,
                            totalExploratoryMoves,
                            totalExploitativeMoves,
                            (double) totalMovesActual / numOfTrials,
                            (double) totalExploratoryMoves / numOfTrials,
                            (double) totalExploitativeMoves / numOfTrials
                    ));


                    if (!reports.isEmpty()) {
                        Shared.setCurrentReportNumber(reports.size() - 1);
                        Shared.setReportText(reports.getLast());
                    }
                    else {
                        Shared.setReportText("No reports to show");
                        Shared.setCurrentReportNumber(-1);
                    }

                    Shared.messageFactory.setTitle("Summary")
                            .setMessage("Simulation completed!<br>We had " + numOfSuccesses + " successes out of " + numOfTrials + " trials.<br>Success Rate: " + String.format("%.1f%%", ((numOfTrials == 0) ? 0.0 : (double) numOfSuccesses / numOfTrials) * 100.0))
                            .setModal(true).create();

                }
                else {

                    Shared.preferenceManager.setStatistic("statsTotalNumAbortedSimulations", Shared.preferenceManager.getStatisticAsInt("statsTotalNumAbortedSimulations") + 1);
                    Shared.log("Simulation aborted by user.", "warning");
                    Shared.setStatusBarText("Simulation aborted!");
                }

            }
            catch (InterruptedException e) {
                Shared.log("Simulation thread unexpectedly interrupted.", "error");
                Thread.currentThread().interrupt();
                Shared.setStatusBarText("Simulation interrupted!");
            }
            catch (CancellationException e) {

                Shared.log("Simulation cancellation processed.", "info");
                Shared.setStatusBarText("Simulation aborted!");
            }
            catch (Exception e) {
                Shared.log("Error during simulation: " + e.getMessage(), "error");
                Shared.setStatusBarText("Simulation failed!");
                Shared.messageFactory.setTitle("Error")
                        .setMessage("An error occurred during simulation:<br>" + e.getMessage())
                        .setModal(true).create();
            }
            finally {

                Shared.toggleStartAbortButton();
                Shared.setTopBoxText("SELECT OPTION OR PRESS START");
                Shared.setStatusBarState("Ready");
                simulationWorker = null;
                simulationPaused = false;

                setSimulationRunning(false);
                Shared.updateAllTables();
                Shared.allowShutdown();
            }
        }
    }

}