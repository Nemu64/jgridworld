package com.nemu.jgridworld;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatMarginBorder;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.W32APIOptions;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Shared {
    private static final DecimalFormat df = new DecimalFormat("0.000");
    private static final String[] statusBarQuotes = {
            //"This quote will never appear! Isn't that strange?",
            "I'm not so good with the advice... Can I interest you in a sarcastic comment?",
            "It also says you were adopted, so that's funny too.",
            "I don't know what you're talking about, but I like the way you think.",
            "If it's the Psychic Network, why do they need a phone number?",
            "I stand before you, because if I stood behind you, you wouldn't be able to see me.",
            "Luck is my middle name. Mind you, my first name is Bad.",
            "Undelete, undelete! Where's the undelete button?",
            "All I wanted to do was make everything better for me!",
            "Asbestos is harmless!",
            "I don't know what you're talking about, but I like the way you think.",
            "What's the point of going out? We're just going to end up back here anyway.",
            "No, no, no, no - or, if I'm honest, yes.",
            "Have you ever tried conditioner on that beard?",
            "Why do they call it rush hour when nothing moves?",
            "Why do you have a self-confidence problem? What are you, stupid?",
            "Honesty is the best policy, but insanity is a better defense.",
            "Everybody needs a helping hand once in a while.",
            "Oh, well, I guess this is just going to be one of those lifetimes.",
            "Who wears short shorts? We wear short shorts!",
            "Hit me with your best shot! Fired away...",
    };
    private static final String positiveNumber = "<html><span style=\"color: #32CD32\">";
    private static final String negativeNumber = "<html><span style=\"color: red\">";
    private static final String endTag = "</span></html>";
    public static Timer windowShakeTimer;
    public static File lastFile = null;

    public static IOS ios = new IOS();
    public static PreferenceManager preferenceManager = new PreferenceManager();
    public static Achievements achievements = new Achievements();
    public static STM stm = new STM();
    public static MainWindow mainWindow = new MainWindow();
    public static MessageFactory messageFactory = new MessageFactory();
    public static PromptFactory promptFactory = new PromptFactory();
    public static StringPromptFactory stringPromptFactory = new StringPromptFactory();
    public static OptionFactory optionFactory = new OptionFactory();

    public Shared() {
        df.setRoundingMode(RoundingMode.HALF_UP);
    }

  
    public static void log(String text) {
        SwingUtilities.invokeLater(() -> mainWindow.log(text));
    }

    public static void log(String text, String logType) {
        SwingUtilities.invokeLater(() -> mainWindow.log(text, logType));
    }

    public static void clearLog() {
        SwingUtilities.invokeLater(() -> mainWindow.clearLog());
    }

    public static String getLogText() {
        if (SwingUtilities.isEventDispatchThread()) {
            return mainWindow.getLogText();
        }
        try {
            AtomicReference<String> logText = new AtomicReference<>();
            SwingUtilities.invokeAndWait(() -> logText.set(mainWindow.getLogText()));
            return logText.get();
        }
        catch (Exception e) {
            return "";
        }
    }

    public static void setAutoScrollLogCheckBoxMenuItem(boolean isSet) {
        SwingUtilities.invokeLater(() -> mainWindow.setAutoScrollLogCheckBoxMenuItem(isSet));
    }

    public static void setShowSimulationCheckBoxMenuItem(boolean isSet) {
        SwingUtilities.invokeLater(() -> mainWindow.setShowSimulationCheckBoxMenuItem(isSet));
    }

    public static void setTopBoxText(String text) {
        SwingUtilities.invokeLater(() -> mainWindow.setTopBoxText(text.toUpperCase()));
    }

    public static void setReportText(String text) {
        SwingUtilities.invokeLater(() -> mainWindow.setReportText(text));
    }

    public static void setCurrentReportNumber(int number) {
        SwingUtilities.invokeLater(() -> mainWindow.setCurrentReportNumber(number));
    }

    public static void setStatusBarText(String text) {
        SwingUtilities.invokeLater(() -> mainWindow.setStatusBarText(text));
    }

    public static String getStatusBarText() {
        if (SwingUtilities.isEventDispatchThread()) {
            return mainWindow.getStatusBarText();
        }
        try {
            AtomicReference<String> statusBarText = new AtomicReference<>();
            SwingUtilities.invokeAndWait(() -> statusBarText.set(mainWindow.getStatusBarText()));
            return statusBarText.get();
        }
        catch (Exception e) {
            return "";
        }
    }

    public static void setStatusBarState(String text) {
        SwingUtilities.invokeLater(() -> mainWindow.setStatusBarState(text));
    }

    public static String getStatusBarState() {
        if (SwingUtilities.isEventDispatchThread()) {
            return mainWindow.getStatusBarState();
        }
        try {
            AtomicReference<String> statusBarState = new AtomicReference<>();
            SwingUtilities.invokeAndWait(() -> statusBarState.set(mainWindow.getStatusBarState()));
            return statusBarState.get();
        }
        catch (Exception e) {
            return "";
        }
    }

    public static void setStatusBarTime(String text) {
        SwingUtilities.invokeLater(() -> mainWindow.setStatusBarTime(text));
    }

    public static void toggleStartAbortButton() {
        SwingUtilities.invokeLater(() -> mainWindow.toggleStartAbortButton());
    }

    public static void updateTable() {
        SwingUtilities.invokeLater(() -> mainWindow.updateTable());
    }

    public static void updateAllTables() {
        SwingUtilities.invokeLater(() -> mainWindow.updateAllTables());
    }

    public static void saveFile() {
        SwingUtilities.invokeLater(() -> mainWindow.saveFile());
    }

    public static void loadFile(String caller, Runnable onComplete) {
        SwingUtilities.invokeLater(() -> mainWindow.loadFile(caller, onComplete));
    }

    public static void loadFile(String caller) {
        loadFile(caller, null);
    }
  

    public static ImageIcon createColorIcon(int r, int g, int b) {
        return new ImageIcon(new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB) {{
            Graphics2D gfx = (Graphics2D) getGraphics();
            try {
                gfx.setColor(new Color(r, g, b));
                gfx.fillRect(0, 0, 16, 16);
            }
            finally {
                gfx.dispose();
            }
        }});
    }


    public static void startWindowShake() {
        if (mainWindow.getExtendedState() == Frame.MAXIMIZED_BOTH) return;
        if (!preferenceManager.getPreferenceAsBoolean("funStuffEnableWindowEffects")) return;
        if (windowShakeTimer != null) {
            windowShakeTimer.stop();
        }
        windowShakeTimer = new Timer(10, e -> {
            if (mainWindow != null) {
                mainWindow.setLocation(mainWindow.getX() - 10, mainWindow.getY());
                mainWindow.setLocation(mainWindow.getX(), mainWindow.getY() + 10);
                mainWindow.setLocation(mainWindow.getX() + 10, mainWindow.getY());
                mainWindow.setLocation(mainWindow.getX(), mainWindow.getY() - 10);
            }
        });
        windowShakeTimer.start();
    }

    public static void stopWindowShake() {
        if (windowShakeTimer != null) {
            windowShakeTimer.stop();
            windowShakeTimer = null;
        }
    }

    public static boolean isWindowShaking() {
        return windowShakeTimer != null && windowShakeTimer.isRunning();
    }

    public static void setRandomStatusBarQuote() {
        if (mainWindow != null) {
            int randomIndex = (int) (Math.random() * statusBarQuotes.length);
            String randomQuote = statusBarQuotes[randomIndex];
            setStatusBarText(randomQuote);
        }
    }

    public static void preventShutdown(String reason) {
        if (!System.getProperty("os.name").toLowerCase().contains("windows")) return;
        if (reason == null || reason.isEmpty()) {
            reason = "BUSY - DO NOT REBOOT!!";
        }
        if (User32.INSTANCE.ShutdownBlockReasonCreate(getHWnd(mainWindow), reason)) {
            log("System shutdown blocked: " + reason);
        }
        else {
            log("Failed to block system shutdown: " + reason);
        }
    }

    public static void allowShutdown() {
        if (!System.getProperty("os.name").toLowerCase().contains("windows")) return;
        if (User32.INSTANCE.ShutdownBlockReasonDestroy(getHWnd(mainWindow))) {
            log("System shutdown block released");
        }
    }

    public static JTable createValueTable() {
        JTable table = new JTable();
        table.setBorder(new FlatMarginBorder());
        table.setFont(new java.awt.Font("Tahoma", 0, 14));
        table.setAutoscrolls(false);
        table.setFillsViewportHeight(true);
        table.setFocusable(false);
        table.setMinimumSize(new java.awt.Dimension(750, 200));
        table.setRequestFocusEnabled(false);
        table.setRowHeight(50);
        table.setRowSelectionAllowed(false);
        table.setDoubleBuffered(true);
        table.setShowGrid(true);
        return table;
    }

    public static DocumentFilter createDocumentFilter(String regex) {
        return new DocumentFilter() {
            private final Pattern pattern = Pattern.compile(regex);

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
                sb.insert(offset, string);
                if (pattern.matcher(sb.toString()).matches()) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
                sb.replace(offset, offset + length, text);
                if (pattern.matcher(sb.toString()).matches()) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
    }

    public static TableModel createValueTableModel() {
        return new javax.swing.table.DefaultTableModel(
                new Object [10][10],
                new String [] {"", "", "", "", "", "", "", "", "", ""}
        ) {
          
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }

    public static String getCellValue(boolean isGridworld, double value, boolean isEditor) {
        if (isGridworld) {
            return switch ((int) value) {
                case 100 -> "<html><span style=\"color: " + (FlatLaf.isLafDark() ? "#00BFFF" : "#0000FF") + "\">\\(^-^)/</span></html>";
                case 50 -> "<html><span style=\"color: #DAA520\">$$$$</span></html>";
                case 10 -> "<html><span style=\"color: #B8860B\">$$</span></html>";
                case -100 -> "<html><span style=\"color: red\">XXXX</span></html>";
                case -999 -> "<html><span style=\"color: gray\">█████</span></html>";
                case 101 -> "↑↑↑↑";
                case 102 -> "↓↓↓↓";
                case 103 -> "←←←←";
                case 104 -> "→→→→";
                case 105 -> "↑←↑←";
                case 106 -> "↑→↑→";
                case 107 -> "↓←↓←";
                case 108 -> "↓→↓→";
                default -> "";
            };
        }
        else {
            if (isEditor && value == 0) {
                return "";
            }
            if (value == 0) {
                return "0";
            }
            String valueString = df.format(value);
            if (value > 0) {
                valueString = positiveNumber + valueString + endTag;
            }
            else if (value < 0) {
                valueString = negativeNumber + valueString + endTag;
            }
            return valueString;
        }

    }

    public static String getCellValue(boolean isGridworld, double value) {
        return getCellValue(isGridworld, value, false);
    }

    public static Pointer getHWnd(Window w) {
        return com.sun.jna.Native.getWindowPointer(w);
    }
    
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean ShutdownBlockReasonCreate(Pointer hWnd, String reason);
        boolean ShutdownBlockReasonDestroy(Pointer hWnd);
    }

}
