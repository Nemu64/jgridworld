
package com.nemu.jgridworld;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.nemu.jgridworld.Shared.*;


public class GridWorldEditor extends javax.swing.JFrame implements PropertyChangeListener {

    
    public GridWorldEditor() {
        initComponents();
        int newRowHeight = Math.max(10, tablePanel.getHeight() / 10);
        int newFontSize = Math.max(8, tablePanel.getHeight() / 30);
        this.getDataFromStm();
        Shared.stm.addPropertyChangeListener(this);
        this.gridworldTable.setRowHeight(newRowHeight);
        this.rewardTable.setRowHeight(newRowHeight);
        this.gridworldTable.setFont(new java.awt.Font("Tahoma", 0, newFontSize));
        this.rewardTable.setFont(new java.awt.Font("Tahoma", 0, newFontSize));
        this.updateTable();
        this.pack();
        this.setLocationRelativeTo(mainWindow);
        this.setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        gridworldItemButtonGroup = new javax.swing.ButtonGroup();
        prizePopupMenu = new javax.swing.JPopupMenu();
        bigPrizeRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        smallPrizeRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        prizePopupButtonGroup = new javax.swing.ButtonGroup();
        editorTabbedPane = new javax.swing.JTabbedPane();
        tablePanel = new javax.swing.JPanel();
        gridworldTable = createValueTable();
        rewardTable = createValueTable();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        manuallySetValuesToggleButton = new javax.swing.JToggleButton();
        addLabel = new javax.swing.JLabel();
        wallToggleButton = new javax.swing.JToggleButton();
        cliffToggleButton = new javax.swing.JToggleButton();
        prizeToggleButton = new javax.swing.JToggleButton();
        agentToggleButton = new javax.swing.JToggleButton();
        instructionsLabel = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();

        prizePopupButtonGroup.add(bigPrizeRadioButtonMenuItem);
        bigPrizeRadioButtonMenuItem.setMnemonic('B');
        bigPrizeRadioButtonMenuItem.setSelected(true);
        bigPrizeRadioButtonMenuItem.setText("Big Prize");
        prizePopupMenu.add(bigPrizeRadioButtonMenuItem);

        prizePopupButtonGroup.add(smallPrizeRadioButtonMenuItem);
        smallPrizeRadioButtonMenuItem.setMnemonic('S');
        smallPrizeRadioButtonMenuItem.setText("Small Prize");
        prizePopupMenu.add(smallPrizeRadioButtonMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("GridWorld Editor");
        setIconImage(Images.TITLE_BAR_ICON);
        setMinimumSize(new java.awt.Dimension(900, 629));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        editorTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                editorTabbedPaneStateChanged(evt);
            }
        });

        gridworldTable.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridworldTable.setModel(createValueTableModel());
        gridworldTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gridworldTableMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 888, Short.MAX_VALUE)
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(gridworldTable, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(gridworldTable, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        editorTabbedPane.addTab("GridWorld View", tablePanel);

        rewardTable.setFont(new java.awt.Font("Tahoma", 0, 14));
        rewardTable.setModel(createValueTableModel());
        rewardTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rewardTableMouseClicked(evt);
            }
        });
        editorTabbedPane.addTab("Reward Values View", rewardTable);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setMnemonic('S');
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        gridworldItemButtonGroup.add(manuallySetValuesToggleButton);
        manuallySetValuesToggleButton.setMnemonic('V');
        manuallySetValuesToggleButton.setSelected(true);
        manuallySetValuesToggleButton.setText("Manually Set Values");

        addLabel.setText("Add:");

        gridworldItemButtonGroup.add(wallToggleButton);
        wallToggleButton.setMnemonic('W');
        wallToggleButton.setText("Wall");

        gridworldItemButtonGroup.add(cliffToggleButton);
        cliffToggleButton.setMnemonic('C');
        cliffToggleButton.setText("Cliff");

        gridworldItemButtonGroup.add(prizeToggleButton);
        prizeToggleButton.setMnemonic('P');
        prizeToggleButton.setText("Prize");
        prizeToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prizeToggleButtonActionPerformed(evt);
            }
        });

        gridworldItemButtonGroup.add(agentToggleButton);
        agentToggleButton.setMnemonic('A');
        agentToggleButton.setText("Agent");

        instructionsLabel.setFont(new java.awt.Font("Tahoma", 0, 18));
        instructionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        instructionsLabel.setText("<html><center>Select an item below, then click a cell to add it to the GridWorld<br>Right-click a cell to remove the item</center></html>");

        resetButton.setMnemonic('R');
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(instructionsLabel)
                    .addComponent(editorTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manuallySetValuesToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wallToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cliffToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prizeToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agentToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editorTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton)
                    .addComponent(manuallySetValuesToggleButton)
                    .addComponent(addLabel)
                    .addComponent(wallToggleButton)
                    .addComponent(cliffToggleButton)
                    .addComponent(prizeToggleButton)
                    .addComponent(agentToggleButton)
                    .addComponent(resetButton))
                .addContainerGap())
        );

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (JTable table : new JTable[] {gridworldTable, rewardTable}) {
            for (int i = 0; i < 10; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);  
            }
        }
        Shared.stm.modifiedGridWorld[stm.agentRowModified][stm.agentColModified] = 100;

        pack();
    }

    private void gridworldTableMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            int row = gridworldTable.rowAtPoint(evt.getPoint());
            int column = gridworldTable.columnAtPoint(evt.getPoint());
            if (row >= 0 && column >= 0) {
                if (row == agentRow && column == agentCol) {
                    messageFactory.setParent(this).setTitle("Invalid Action").setMessage("You cannot place an item in the agent's cell").setModal(true).create();
                    return;
                }
                if (manuallySetValuesToggleButton.isSelected()) {
                    double newValue;
                    while (true) {
                        try {
                            String input = stringPromptFactory.setParent(this).setTitle("Enter Value").setMessage("What value should be placed in this cell?").setRegexPattern("^-?(\\d+)?(\\.\\d*)?$").create().getString();
                            if (input.isEmpty()) {
                                return;
                            }
                            newValue = Double.parseDouble(input);
                            if (newValue < -99 || newValue > 99) {
                                throw new NumberFormatException("Value out of range");
                            }
                        }
                        catch (NumberFormatException e) {
                            messageFactory.setParent(this).setTitle("Invalid Value").setMessage(e.getMessage().contains("out of range") ? "Value must be between -99 and 99" : "Invalid value. Please enter a number.").setModal(true).create();
                            continue;
                        }
                        break;
                    }
                    rewardValues[row][column] = newValue;
                }
                else if (wallToggleButton.isSelected()) {
                    gridWorld[row][column] = -999;
                    rewardValues[row][column] = -999;
                }
                else if (cliffToggleButton.isSelected()) {
                    gridWorld[row][column] = -100;
                    rewardValues[row][column] = -100;
                }
                else if (prizeToggleButton.isSelected()) {
                    int prizeValue = bigPrizeRadioButtonMenuItem.isSelected() ? 50 : 10;
                    gridWorld[row][column] = prizeValue;
                    rewardValues[row][column] = prizeValue;
                }
                else if (agentToggleButton.isSelected()) {
                  
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (gridWorld[i][j] == 100) {
                                gridWorld[i][j] = 0.0;
                            }
                        }
                    }
                    gridWorld[row][column] = 100;
                    agentRow = row;
                    agentCol = column;
                }
            }
            updateTable();
        }
        else if (evt.getButton() == MouseEvent.BUTTON3) {
            int row = gridworldTable.rowAtPoint(evt.getPoint());
            int column = gridworldTable.columnAtPoint(evt.getPoint());
            if (row == agentRow && column == agentCol) {
                messageFactory.setParent(this).setParent(this).setTitle("Invalid Action").setMessage("You cannot remove the agent").setModal(true).create();
                return;
            }
            if (row >= 0 && column >= 0) {
                gridWorld[row][column] = 0.0;
                rewardValues[row][column] = 0.0;
            }
            updateTable();
        }
    }

    private void editorTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {
        updateTable();
    }

    private void rewardTableMouseClicked(java.awt.event.MouseEvent evt) {
        gridworldTableMouseClicked(evt);
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        boolean prizeFound = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (rewardValues[i][j] == 50 || rewardValues[i][j] == 10) {
                    prizeFound = true;
                    break;
                }
            }
        }
        if (!prizeFound) {
            messageFactory.setParent(this).setTitle("No Prizes Found").setMessage("You must place at least one prize in the GridWorld.").setModal(true).create();
            return;
        }
        if (!Shared.stm.isPathToPrizeAvailable(rewardValues, agentRow, agentCol)) {
            messageFactory.setParent(this).setTitle("Invalid GridWorld").setMessage("The agent cannot reach any prize location.<br>Please modify walls or cliffs to create a valid path.").setModal(true).create();
            return;
        }
        stm.applyGridModifications(rewardValues, agentRow, agentCol, gridWorld);
        messageFactory.setParent(this).setTitle("Changes Saved").setMessage("Your changes have been saved." + ((stm.simulationRunning) ? "<br>They will be applied to the next simulation." : "")).setModal(true).create();
        preferenceManager.setStatistic("statsTotalNumGridWorldsCreated", preferenceManager.getStatisticAsInt("statsTotalNumGridWorldsCreated") + 1);
        if (promptFactory.setParent(this).setTitle("Finished Editing?").setMessage("Are you finished editing the GridWorld?").setModal(true).create().getAnswer()) {
            this.dispose();
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (haveChangesBeenMade() && !promptFactory.setParent(this).setTitle("Discard Changes").setMessage("Are you sure you want to discard any unsaved changes?").setModal(true).create().getAnswer()) {
            return;
        }
        this.dispose();
    }

    private void getDataFromStm() {
        this.gridWorld = stm.deepCopyArray(stm.modifiedGridWorld);
        this.rewardValues = stm.deepCopyArray(stm.modifiedReward);
        this.agentRow = stm.agentRowModified;
        this.agentCol = stm.agentColModified;
    }

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (promptFactory.setParent(this).setTitle("Reset GridWorld").setMessage("Are you sure you want to reset the GridWorld?<br><span style='color: red'>This cannot be undone!</span>").setModal(true).create().getAnswer()) {
            agentRow = stm.agentRowDefault;
            agentCol = stm.agentColDefault;
            gridWorld = new double[10][10];
            gridWorld[agentRow][agentCol] = 100;
            gridWorld[4][9] = stm.bigPrize;
            rewardValues = new double[10][10];
            rewardValues[4][9] = stm.bigPrize;
            stm.resetToDefaultGrid();
            updateTable();
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        cancelButtonActionPerformed(null);
    }

    private void prizeToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {
        prizePopupMenu.show(this, prizeToggleButton.getX(), prizeToggleButton.getY() - prizeToggleButton.getHeight());
    }

    private boolean haveChangesBeenMade() {
        if (agentRow != stm.agentRowModified || agentCol != stm.agentColModified) {
            return true;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (rewardValues[i][j] != stm.modifiedReward[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateTable() {
        if (editorTabbedPane.getSelectedIndex() == 0) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    gridworldTable.setValueAt(getCellValue(true, gridWorld[i][j]), i, j);
                }
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    rewardTable.setValueAt(getCellValue(false, rewardValues[i][j], true), i, j);
                }
            }
        }
    }

  
    private javax.swing.JLabel addLabel;
    private javax.swing.JToggleButton agentToggleButton;
    private javax.swing.JRadioButtonMenuItem bigPrizeRadioButtonMenuItem;
    private javax.swing.JButton cancelButton;
    private javax.swing.JToggleButton cliffToggleButton;
    private javax.swing.JTabbedPane editorTabbedPane;
    private javax.swing.ButtonGroup gridworldItemButtonGroup;
    private javax.swing.JTable gridworldTable;
    private javax.swing.JLabel instructionsLabel;
    private javax.swing.JToggleButton manuallySetValuesToggleButton;
    private javax.swing.ButtonGroup prizePopupButtonGroup;
    private javax.swing.JPopupMenu prizePopupMenu;
    private javax.swing.JToggleButton prizeToggleButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JTable rewardTable;
    private javax.swing.JButton saveButton;
    private javax.swing.JRadioButtonMenuItem smallPrizeRadioButtonMenuItem;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JToggleButton wallToggleButton;
  
    private double[][] gridWorld = new double[10][10];
    private double[][] rewardValues = new double[10][10];
    private int agentRow, agentCol;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (STM.CONFIGURATION_LOADED_PROPERTY.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(this::getDataFromStm);
            SwingUtilities.invokeLater(this::updateTable);
        }
    }

    @Override
    public void dispose() {
        Shared.stm.removePropertyChangeListener(this);
        super.dispose();
    }
}
