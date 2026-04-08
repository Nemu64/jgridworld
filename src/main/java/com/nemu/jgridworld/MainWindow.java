package com.nemu.jgridworld;

import com.formdev.flatlaf.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import static com.nemu.jgridworld.Shared.*;




public class MainWindow extends javax.swing.JFrame {

    
    public MainWindow() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        statusBar = new javax.swing.JTextField();
        statusBarTime = new javax.swing.JTextField();
        statusBarState = new javax.swing.JTextField();
        contentPanel = new javax.swing.JPanel();
        startAbortButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        gridworldTabPane = new javax.swing.JTabbedPane();
        simTable = createValueTable();
        averageValuesTable = createValueTable();
        upValuesTable = createValueTable();
        downValuesTable = createValueTable();
        leftValuesTable = createValueTable();
        rightValuesTable = createValueTable();
        upLeftValuesTable = createValueTable();
        downLeftValuesTable = createValueTable();
        upRightValuesTable = createValueTable();
        downRightValuesTable = createValueTable();
        reportsTab = new javax.swing.JPanel();
        reportsScrollPane = new javax.swing.JScrollPane();
        reportsTextArea = new javax.swing.JTextArea();
        previousReportButton = new javax.swing.JButton();
        nextReportButton = new javax.swing.JButton();
        voidReportButton = new javax.swing.JButton();
        clearAllReportsButton = new javax.swing.JButton();
        reportNumberFieldLabel = new javax.swing.JLabel();
        reportNumberTextField = new javax.swing.JTextField();
        goToReportButton = new javax.swing.JButton();
        printReportButton = new javax.swing.JButton();
        simLogScrollPane = new javax.swing.JScrollPane();
        simLog = new javax.swing.JTextPane();
        topBox = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newSimMenuItem = new javax.swing.JMenuItem();
        newWindowMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editGridWorldMenuItem = new javax.swing.JMenuItem();
        simOptionsMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        autoScrollLogCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        showSimulationCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        statisticsMenuItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        preferencesMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("JGridWorld");
        setIconImage(Images.TITLE_BAR_ICON);
        setMinimumSize(new java.awt.Dimension(1360, 750));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        statusBar.setEditable(false);
        statusBar.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        statusBar.setText("Welcome to JGridWorld!");
        statusBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 0, 0), javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(0, 1, 0, 0))));
        statusBar.setFocusable(false);

        statusBarTime.setEditable(false);
        statusBarTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        statusBarTime.setText("Waiting for clock...");
        statusBarTime.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusBarTime.setFocusable(false);

        statusBarState.setEditable(false);
        statusBarState.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        statusBarState.setText("Ready");
        statusBarState.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusBarState.setFocusable(false);

        startAbortButton.setBackground(new java.awt.Color(34, 139, 34));
        startAbortButton.setFont(new java.awt.Font("Tahoma", 1, 24));
        startAbortButton.setForeground(new java.awt.Color(255, 255, 255));
        startAbortButton.setMnemonic('S');
        startAbortButton.setText("START");
        startAbortButton.setPreferredSize(new java.awt.Dimension(300, 35));
        startAbortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAbortButtonActionPerformed(evt);
            }
        });

        tablePanel.setMinimumSize(new java.awt.Dimension(750, 500));
        tablePanel.setPreferredSize(new java.awt.Dimension(750, 500));

        gridworldTabPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        gridworldTabPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gridworldTabPaneStateChanged(evt);
            }
        });

        simTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("GridWorld", simTable);

        averageValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Average Values", averageValuesTable);

        upValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Up Values", upValuesTable);

        downValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Down Values", downValuesTable);

        leftValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Left Values", leftValuesTable);

        rightValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Right Values", rightValuesTable);

        upLeftValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Up-Left Values", upLeftValuesTable);

        downLeftValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Down-Left Values", downLeftValuesTable);

        upRightValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Up-Right Values", upRightValuesTable);

        downRightValuesTable.setModel(createValueTableModel());
        gridworldTabPane.addTab("Down-Right Values", downRightValuesTable);

        reportsScrollPane.setFocusable(false);

        reportsTextArea.setEditable(false);
        reportsTextArea.setFont(new java.awt.Font("Tahoma", 0, 18));
        reportsTextArea.setText("No reports to show");
        reportsTextArea.setFocusable(false);
        reportsScrollPane.setViewportView(reportsTextArea);

        previousReportButton.setMnemonic('O');
        previousReportButton.setText("Previous Report");
        previousReportButton.setToolTipText("Hold Shift to go to first report");
        previousReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousReportButtonActionPerformed(evt);
            }
        });

        nextReportButton.setMnemonic('N');
        nextReportButton.setText("Next Report");
        nextReportButton.setToolTipText("Hold Shift to go to last report");
        nextReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextReportButtonActionPerformed(evt);
            }
        });

        voidReportButton.setMnemonic('V');
        voidReportButton.setText("Void Report");
        voidReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voidReportButtonActionPerformed(evt);
            }
        });

        clearAllReportsButton.setMnemonic('C');
        clearAllReportsButton.setText("Clear All Reports");
        clearAllReportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllReportsButtonActionPerformed(evt);
            }
        });

        reportNumberFieldLabel.setDisplayedMnemonic('R');
        reportNumberFieldLabel.setLabelFor(reportNumberTextField);
        reportNumberFieldLabel.setText("Report Number:");

        reportNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reportNumberTextFieldKeyPressed(evt);
            }
        });

        goToReportButton.setText("→");
        goToReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToReportButtonActionPerformed(evt);
            }
        });

        printReportButton.setMnemonic('P');
        printReportButton.setText("Print Report");
        printReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportsTabLayout = new javax.swing.GroupLayout(reportsTab);
        reportsTab.setLayout(reportsTabLayout);
        reportsTabLayout.setHorizontalGroup(
            reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reportsScrollPane)
                    .addGroup(reportsTabLayout.createSequentialGroup()
                        .addComponent(previousReportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextReportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(voidReportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearAllReportsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reportNumberFieldLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reportNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(goToReportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(printReportButton))))
        );
        reportsTabLayout.setVerticalGroup(
            reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsTabLayout.createSequentialGroup()
                .addComponent(reportsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousReportButton)
                    .addComponent(nextReportButton)
                    .addComponent(voidReportButton)
                    .addComponent(clearAllReportsButton)
                    .addComponent(printReportButton)
                    .addComponent(reportNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goToReportButton)
                    .addComponent(reportNumberFieldLabel))
                .addGap(14, 14, 14))
        );

        ((PlainDocument) reportNumberTextField.getDocument()).setDocumentFilter(Shared.createDocumentFilter("[0-9]+"));

        gridworldTabPane.addTab("Reports", reportsTab);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (Component component : gridworldTabPane.getComponents()) {
            if (component instanceof JTable table) {
                for (int i = 0; i < 10; i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
            }
        }

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gridworldTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1420, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gridworldTabPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        gridworldTabPane.setSelectedIndex(preferenceManager.getPreferenceAsInt("uiDefaultStartupTab"));

        simLogScrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        simLogScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        simLogScrollPane.setMinimumSize(new java.awt.Dimension(300, 582));
        simLogScrollPane.setPreferredSize(new java.awt.Dimension(300, 582));

        simLog.setContentType("text/html");
        simLog.setFont(new java.awt.Font("Tahoma", 0, 12));
        simLog.setFocusable(false);
        simLogScrollPane.setViewportView(simLog);

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startAbortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(simLogScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addComponent(simLogScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startAbortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );

        topBox.setFont(new java.awt.Font("Tahoma", 0, 24));
        topBox.setText("SELECT OPTION OR PRESS START");
        topBox.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        newSimMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        newSimMenuItem.setMnemonic('N');
        newSimMenuItem.setText("New Simulation");
        newSimMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSimMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newSimMenuItem);

        newWindowMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        newWindowMenuItem.setMnemonic('W');
        newWindowMenuItem.setText("New Window");
        newWindowMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newWindowMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newWindowMenuItem);

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openMenuItem.setMnemonic('O');
        openMenuItem.setText("Open...");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveMenuItem.setMnemonic('S');
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveAsMenuItem.setMnemonic('A');
        saveAsMenuItem.setText("Save As...");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('X');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('E');
        editMenu.setText("Edit");

        editGridWorldMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        editGridWorldMenuItem.setMnemonic('E');
        editGridWorldMenuItem.setText("Edit GridWorld");
        editGridWorldMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editGridWorldMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editGridWorldMenuItem);

        simOptionsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        simOptionsMenuItem.setMnemonic('O');
        simOptionsMenuItem.setText("Simulation Options");
        simOptionsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simOptionsMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(simOptionsMenuItem);

        menuBar.add(editMenu);

        viewMenu.setMnemonic('W');
        viewMenu.setText("View");

        autoScrollLogCheckBoxMenuItem.setMnemonic('A');
        autoScrollLogCheckBoxMenuItem.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("uiAutoScrollLog"));
        autoScrollLogCheckBoxMenuItem.setText("Auto-Scroll Log");
        autoScrollLogCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoScrollLogCheckBoxMenuItemActionPerformed(evt);
            }
        });
        autoScrollLogCheckBoxMenuItem.putClientProperty("CheckBoxMenuItem.doNotCloseOnMouseClick", true);
        viewMenu.add(autoScrollLogCheckBoxMenuItem);

        showSimulationCheckBoxMenuItem.setMnemonic('S');
        showSimulationCheckBoxMenuItem.setSelected(Shared.stm.showSimulationProgress);
        showSimulationCheckBoxMenuItem.setText("Show Simulation");
        showSimulationCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSimulationCheckBoxMenuItemActionPerformed(evt);
            }
        });
        showSimulationCheckBoxMenuItem.putClientProperty("CheckBoxMenuItem.doNotCloseOnMouseClick", true);
        viewMenu.add(showSimulationCheckBoxMenuItem);

        statisticsMenuItem.setMnemonic('S');
        statisticsMenuItem.setText("Statistics...");
        statisticsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(statisticsMenuItem);

        menuBar.add(viewMenu);

        settingsMenu.setMnemonic('T');
        settingsMenu.setText("Settings");

        preferencesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        preferencesMenuItem.setMnemonic('P');
        preferencesMenuItem.setText("Preferences...");
        preferencesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesMenuItemActionPerformed(evt);
            }
        });
        settingsMenu.add(preferencesMenuItem);

        menuBar.add(settingsMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");

        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText("About JGridWorld");
        aboutMenuItem.setToolTipText("");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(statusBar)
                .addGap(0, 0, 0)
                .addComponent(statusBarState, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(statusBarTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(topBox, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusBarState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(statusBarTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }

    public void log(String text, String logType) {
      
        if (logHistory.size() > preferenceManager.getPreferenceAsInt("uiMaxLogSize")) {
            logHistory.removeFirst();
        }
        logHistory.add(new LogEntry(text, logType));
        SimpleAttributeSet type = determineLogAttributes(logType);
        int oldScrollPosition = simLog.getCaretPosition();
        try {
            StyledDocument doc = simLog.getStyledDocument();
            if (doc.getLength() > 0) {
                text = "\n" + text;
            }
            doc.insertString(doc.getLength(), text, type);
          
            if (preferenceManager.getPreferenceAsBoolean("uiAutoScrollLog")) simLog.setCaretPosition(doc.getLength());
            else simLog.setCaretPosition(oldScrollPosition);
        } catch (BadLocationException ignored) {}
    }

    public void log(String text) {
        log(text, "");
    }

    public void clearLog() {
        simLog.setText("");
        logHistory.clear();
    }

    public String getLogText() {
        if (simLog.getDocument().getLength() == 0) {
            return "";
        }
        try {
            return simLog.getDocument().getText(0, simLog.getDocument().getLength());
        } catch (BadLocationException e) {
            return "";
        }
    }

    public void updateLogColors() {
        StyledDocument doc = simLog.getStyledDocument();
        int oldScrollPosition = simLog.getCaretPosition();
        try {
            doc.remove(0, doc.getLength());
            boolean firstLine = true;
            for (LogEntry entry : logHistory) {
                SimpleAttributeSet type = determineLogAttributes(entry.logType());
                if (firstLine) {
                    doc.insertString(doc.getLength(), entry.text(), type);
                    firstLine = false;
                }
                else {
                    doc.insertString(doc.getLength(), "\n" + entry.text(), type);
                }
            }
            if (preferenceManager.getPreferenceAsBoolean("uiAutoScrollLog")) simLog.setCaretPosition(doc.getLength());
            else simLog.setCaretPosition(oldScrollPosition);
        }
        catch (BadLocationException ignored) {}
    }

    private SimpleAttributeSet determineLogAttributes(String logType) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        attributes.addAttribute(StyleConstants.FontFamily, UIManager.getFont("TextPane.font").getFamily());
        attributes.addAttribute(StyleConstants.FontSize, UIManager.getFont("TextPane.font").getSize() + 3.5);
        switch (logType) {
            case "warning" -> attributes.addAttribute(StyleConstants.Foreground, (FlatLaf.isLafDark() ? new Color(255, 165, 0) : new Color(255, 140, 0)));
            case "error" -> attributes.addAttribute(StyleConstants.Foreground, (FlatLaf.isLafDark() ? new Color(178, 34, 34) : new Color(255, 0, 0)));
            case "info" -> attributes.addAttribute(StyleConstants.Foreground, (FlatLaf.isLafDark() ? new Color(0, 222, 0) : new Color(0, 128, 0)));
            default -> attributes.addAttribute(StyleConstants.Foreground, UIManager.getColor("TextPane.foreground"));
        };
        return attributes;
    }

    public void updateTable(int tableIndex) {
        if (tableIndex == 10) {
            return;
        }
        double[][] values = Shared.stm.getTableValues(new String[] {"gridWorld", "averageValues", "upValues", "downValues", "leftValues", "rightValues", "upLeftValues", "downLeftValues", "upRightValues", "downRightValues"}[tableIndex]);
      
      
        if (tableIndex == 0) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    simTable.setValueAt(Shared.getCellValue(true, values[i][j]), i, j);
                }
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String valueString = Shared.getCellValue(false, values[i][j]);
                    switch (tableIndex) {
                        case 1 -> averageValuesTable.setValueAt(valueString, i, j);
                        case 2 -> upValuesTable.setValueAt(valueString, i, j);
                        case 3 -> downValuesTable.setValueAt(valueString, i, j);
                        case 4 -> leftValuesTable.setValueAt(valueString, i, j);
                        case 5 -> rightValuesTable.setValueAt(valueString, i, j);
                        case 6 -> upLeftValuesTable.setValueAt(valueString, i, j);
                        case 7 -> downLeftValuesTable.setValueAt(valueString, i, j);
                        case 8 -> upRightValuesTable.setValueAt(valueString, i, j);
                        case 9 -> downRightValuesTable.setValueAt(valueString, i, j);
                        default -> {
                            log("Can't find table to update", "error");
                        }
                    }
                }
            }
        }
    }

    public void updateTable() {
        updateTable(gridworldTabPane.getSelectedIndex());
    }

    public void updateAllTables() {
        for (int i = 0; i < gridworldTabPane.getTabCount() - (Shared.stm.kingsMoves ? 1 : 5); i++) {
            updateTable(i);
        }
    }

    private void newSimMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (Shared.preferenceManager.getPromptFlag("noAskNewSim") || Shared.promptFactory.setMessage("Are you sure you want to start a new simulation?<br><span style='color: red'>Unsaved data will be lost!</span>").setTitle("Confirm New Simulation").setDNAA(true).setPreferenceKey("noAskNewSim").setModal(true).setDefaultOption("no").create().getAnswer()) {
            log("Cleaning up stray matter...");
            Shared.stm = new STM();
            updateAllTables();
            reportsTextArea.setText("No reports to show");
            setCurrentReportNumber(-1);
        }
    }

    private void simOptionsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (simOptionsWindow == null || !simOptionsWindow.isDisplayable()) {
                simOptionsWindow = new SimOptionsWindow();
                simOptionsWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        simOptionsWindow = null;
                    }
                });
            }
            else {
                if (!simOptionsWindow.isVisible()) {
                    simOptionsWindow.setVisible(true);
                }
                simOptionsWindow.toFront();
                simOptionsWindow.requestFocus();
            }
        } catch (Exception e) {
            log("Error opening simulation options window: " + e.getMessage() + "Perhaps the reference was not cleared properly?", "error");
        }
    }

    private void formComponentResized(java.awt.event.ComponentEvent evt) {
        if (getExtendedState() == Frame.MAXIMIZED_BOTH) Shared.stopWindowShake();
        resizeTable();
        resizeReport();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        log("Requesting confirmation of close command...");
        if (Shared.preferenceManager.getPromptFlag("noAskCloseProgram") || Shared.promptFactory.setMessage("Are you sure you want to exit?").setTitle("Confirm Exit").setDNAA(true).setPreferenceKey("noAskCloseProgram").setApplicationModal(true).setDefaultOption("no").create().getAnswer()) {
            log("JGridWorld shutting down...", "info");
            log("Saving preferences...", "info");
            preferenceManager.onExit();
            log("Done! See you next time!", "info");
            System.exit(0);
        }
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        formWindowClosing(null);        
    }

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        Shared.messageFactory.setMessage("JGridWorld: A GUI reimagining of DGM GridWorld<br>Created by Nemu<br>Version 1.0 (Apr 08/2026)<br>").setTitle("About").setModal(true).create();
    }

    private void startAbortButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Shared.stm.STMTick();
    }

    private void gridworldTabPaneStateChanged(javax.swing.event.ChangeEvent evt) {
        resizeTable();      
        if (gridworldTabPane.getSelectedIndex() != 10) {
            updateTable();
        }
    }

    private void previousReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentReportNumber == 0) {
            if (!Shared.preferenceManager.getPreferenceAsBoolean("noShowNoPrevReportsWarning")) {
                Shared.messageFactory.setMessage("No previous reports to show.").setDNSA(true).setPreferenceKey("noShowNoPrevReportsWarning").setModal(true).create();
            }
            else log("No previous reports to show", "warning");
        }
        else {
            if ((evt.getModifiers() & InputEvent.SHIFT_MASK) != 0) currentReportNumber = 0;
            else currentReportNumber--;
            setReportText(Shared.stm.getReport(currentReportNumber));
            reportNumberTextField.setText(String.valueOf(currentReportNumber + 1));
        }
    }

    private void nextReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (Shared.stm.getNumOfReports() == 0 || currentReportNumber == Shared.stm.getNumOfReports() - 1) {
            if (!Shared.preferenceManager.getPreferenceAsBoolean("noShowNoNextReportsWarning")) {
                Shared.messageFactory.setMessage("No more reports to show.").setDNSA(true).setPreferenceKey("noShowNoNextReportsWarning").setModal(true).create();
            }
            else log("No more reports to show", "warning");
        }
        else {
            if ((evt.getModifiers() & InputEvent.SHIFT_MASK) != 0) currentReportNumber = Shared.stm.getNumOfReports() - 1;
            else currentReportNumber++;
            setReportText(Shared.stm.getReport(currentReportNumber));
            reportNumberTextField.setText(String.valueOf(currentReportNumber + 1));
        }
    }

    private void voidReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (Shared.stm.getNumOfReports() == 0) {
            Shared.messageFactory.setMessage("No reports to void.").setModal(true).create();
            return;
        }
        if (Shared.preferenceManager.getPromptFlag("noAskVoidReport") || Shared.promptFactory.setMessage("Are you sure you want to void this report?").setTitle("Confirm Void").setDNAA(true).setPreferenceKey("noAskVoidReport").setModal(true).setDefaultOption("no").create().getAnswer()) {
            int reason = optionFactory.setParent(this).setTitle("Select Void Reason").setMessage("Select a reason for voiding this report:").setOptions(new String[] {"User Error", "System Error", "Data Discrepancy"}).setModal(true).create().getSelectedOption();
            if (reason == -1) {
                return;
            }
            log("Voiding report " + (currentReportNumber + 1) + " for reason: " + new String[] {"User Error", "System Error", "Data Discrepancy"}[reason]);
            Shared.stm.voidReport(currentReportNumber, reason);
            if (currentReportNumber > 0) {
                currentReportNumber--;
            }
            if (Shared.stm.getNumOfReports() > 0) {
                setReportText(Shared.stm.getReport(currentReportNumber));
                reportNumberTextField.setText(String.valueOf(currentReportNumber + 1));
            }
            else {
                reportsTextArea.setText("No reports to show");
                reportNumberTextField.setText("");
                currentReportNumber = -1;
            }
        }
    }

    private void clearAllReportsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (Shared.stm.getNumOfReports() == 0) {
            Shared.messageFactory.setMessage("No reports to clear.").setModal(true).create();
            return;
        }
        if (Shared.preferenceManager.getPromptFlag("noAskClearReports") || Shared.promptFactory.setMessage("Are you sure you want to clear all reports?").setTitle("Confirm Clear").setDNAA(true).setPreferenceKey("noAskClearReports").setModal(true).setDefaultOption("no").create().getAnswer()) {
            log("Clearing all reports...");
            Shared.stm.clearReports();
            reportsTextArea.setText("No reports to show");
            reportNumberTextField.setText("");
            currentReportNumber = -1;
        }
    }

    private void goToReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (reportNumberTextField.getText().isEmpty()) {
            log("No report number entered", "warning");
            Shared.messageFactory.setTitle("No Input").setMessage("Please enter a report number.").setModal(true).create();
            return;
        }
        try {
            if (Shared.stm.getNumOfReports() == 0) {
                Shared.messageFactory.setMessage("No reports to show.").setModal(true).create();
                return;
            }
            int reportNumber = Integer.parseInt(reportNumberTextField.getText()) - 1;
            if (reportNumber == currentReportNumber) {
                log("Already on report " + (reportNumber + 1));
                return;
            }
            if (reportNumber == -1) {
                log("Report number must be non-zero", "error");
                Shared.messageFactory.setTitle("Error").setMessage("Report number must be non-zero.").setModal(true).create();
                return;
            }
            if (reportNumber < 0 || reportNumber >= Shared.stm.getNumOfReports()) {
                log("Invalid report number", "error");
                Shared.messageFactory.setTitle("Error").setMessage("Can't find report number " + (reportNumber + 1) + ".").setModal(true).create();
                return;
            }
            currentReportNumber = reportNumber;
            setReportText(Shared.stm.getReport(currentReportNumber));
        } catch (NumberFormatException e) {
            log("Invalid report number format", "error");
        }
    }

    private void printReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (Shared.stm.getNumOfReports() == 0) {
            Shared.messageFactory.setMessage("No reports to print.").setModal(true).create();
            return;
        }
        if (currentReportNumber < 0 || currentReportNumber >= stm.getNumOfReports()) {
            Shared.messageFactory.setTitle("Error").setMessage("Can't find report to print.").setModal(true).create();
            return;
        }
      
        if (Shared.promptFactory.setTitle("Print Report").setMessage("Are you sure you want to print this report?").setDNAA(true).setPreferenceKey("noAskPrintReport").setModal(true).setDefaultOption("yes").create().getAnswer()) {
            final JTextArea printTextArea = new JTextArea(stm.getReport(currentReportNumber));
            printReportButton.setEnabled(false);
            setStatusBarText("Printing report " + (currentReportNumber + 1) + "...");
            setStatusBarState("BUSY");
            SwingWorker<Boolean, Void> printWorker = new SwingWorker<>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                  
                  
                  
                    try {
                        return printTextArea.print();
                    } catch (PrinterException e) {
                        log("Error during printing thread: " + e.getMessage(), "error");
                      
                        throw e;
                    }
                }

                @Override
                protected void done() {
                  
                    try {
                        boolean printedSuccessfully = get();
                        if (printedSuccessfully) {
                            log("Report printed successfully.", "info");
                            setStatusBarText("Report " + (currentReportNumber + 1) + " sent to printer.");
                            preferenceManager.setStatistic("statsTotalNumReportsPrinted", preferenceManager.getStatisticAsInt("statsTotalNumReportsPrinted") + 1);
                        } else {
                            log("Report printing cancelled by user or system.", "warning");
                            setStatusBarText("Printing cancelled for report " + (currentReportNumber + 1) + ".");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        log("Printing thread interrupted.", "error");
                        setStatusBarText("Printing interrupted.");
                    } catch (java.util.concurrent.ExecutionException e) {
                      
                        Throwable cause = e.getCause();
                        log("Error executing print job: " + cause.getMessage(), "error");
                        setStatusBarText("Printing failed.");
                      
                        Shared.messageFactory.setTitle("Printing Error")
                                             .setMessage("Could not print report:<br>" + cause.getMessage())
                                             .setModal(true).create();
                    } finally {
                      
                        printReportButton.setEnabled(true);
                        setStatusBarState("Ready");
                        setStatusBarText("Select option or press Start");
                    }
                }
            };
            printWorker.execute();
        }
    }

    private void newWindowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
      
        log("Attempting to launch new window...");
        try {
            String javaHome = System.getProperty("java.home");
            String osName = System.getProperty("os.name").toLowerCase();
            String javaExecutable = javaHome + File.separator + "bin" + File.separator + (osName.contains("win") ? "java.exe" : "java");
            String classpath = System.getProperty("java.class.path");
            String mainClass = "com.gerneralmagic.jgridworld.MainWindow";
            ArrayList<String> command = new ArrayList<>();
            command.add(javaExecutable);
            command.add("-cp");
            command.add(classpath);
            command.add(mainClass);
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.start();
            log("New window opened!", "info");
        }
        catch (Exception e) {
            log("Error launching new window: " + e.getMessage(), "error");
        }

    }

    private void reportNumberTextFieldKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            goToReportButtonActionPerformed(null);
        }
    }

    private void editGridWorldMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (gridWorldEditor == null || !gridWorldEditor.isDisplayable()) {
                gridWorldEditor = new GridWorldEditor();
                gridWorldEditor.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        gridWorldEditor = null;
                    }
                });
            }
            else {
                if (!gridWorldEditor.isVisible()) {
                    gridWorldEditor.setVisible(true);
                }
                gridWorldEditor.toFront();
                gridWorldEditor.requestFocus();
            }
        } catch (Exception e) {
            log("Error opening GridWorld window: " + e.getMessage() + "Perhaps the reference was not cleared properly?", "error");
        }
    }

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        saveFile();
    }

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        loadFile("self");
    }

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (Shared.lastFile == null) {
            saveAsMenuItemActionPerformed(null);
        }
        else {
            if (Shared.preferenceManager.getPromptFlag("noAskOverwriteConfiguration") || Shared.promptFactory.setMessage("Are you sure you want to overwrite the current configuration file?").setTitle("Confirm Overwrite").setDNAA(true).setPreferenceKey("noAskOverwriteConfiguration").setModal(true).setDefaultOption("no").create().getAnswer()) {
                try {
                    Shared.stm.saveConfiguration(Shared.lastFile);
                }
                catch (IOException e) {
                    log("Error saving configuration: " + e.getMessage(), "error");
                    Shared.messageFactory.setTitle("Save Error").setMessage("An error occurred while saving the configuration: " + e.getMessage()).setModal(true).create();
                }
            }
        }
    }

    private void preferencesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (preferencesWindow == null || !preferencesWindow.isDisplayable()) {
                preferencesWindow = new PreferencesWindow();
                preferencesWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        preferencesWindow = null;
                    }
                });
            }
            else {
                if (!preferencesWindow.isVisible()) {
                    preferencesWindow.setVisible(true);
                }
                preferencesWindow.toFront();
                preferencesWindow.requestFocus();
            }
        } catch (Exception e) {
            log("Error opening preferences window: " + e.getMessage() + "Perhaps the reference was not cleared properly?", "error");
        }
    }

    private void statisticsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (statisticsWindow == null || !statisticsWindow.isDisplayable()) {
                statisticsWindow = new StatisticsWindow();
                statisticsWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        statisticsWindow = null;
                    }
                });
            }
            else {
                if (!statisticsWindow.isVisible()) {
                    statisticsWindow.setVisible(true);
                }
                statisticsWindow.toFront();
                statisticsWindow.requestFocus();
            }
        } catch (Exception e) {
            log("Error opening statistics window: " + e.getMessage() + "Perhaps the reference was not cleared properly?", "error");
        }
    }

    private void showSimulationCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        stm.showSimulationProgress = showSimulationCheckBoxMenuItem.isSelected();
    }

    private void autoScrollLogCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        Shared.preferenceManager.setPreference("uiAutoScrollLog", autoScrollLogCheckBoxMenuItem.isSelected());
    }

    private void resizeTable() {
        Component selectedComponent = gridworldTabPane.getSelectedComponent();
        if (selectedComponent instanceof JTable table) {
          
            int availableWidth = table.getWidth();
            int columnCount = table.getColumnCount();

            if (columnCount > 0 && availableWidth > 0) {
              
                int columnWidth = availableWidth / columnCount;
              
                int remainder = availableWidth % columnCount;

                for (int i = 0; i < columnCount; i++) {
                    int currentColumnWidth = columnWidth;
                    if (i < remainder) {
                        currentColumnWidth++;
                    }
                  
                    table.getColumnModel().getColumn(i).setPreferredWidth(currentColumnWidth);
                  
                  
                  
                }
            }


          
            int newRowHeight = Math.max(10, tablePanel.getHeight() / 10);
            int newFontSize = Math.max(8, tablePanel.getHeight() / 30);
            table.setRowHeight(newRowHeight);
          
            if (table.getFont().getSize() != newFontSize) {
                table.setFont(new java.awt.Font("Tahoma", 0, newFontSize));
            }
        }
    }

    private void resizeReport() {
        int newFontSize = Math.max(8, tablePanel.getHeight() / 30);
        reportsTextArea.setFont(new java.awt.Font("Tahoma", 0, newFontSize));
        reportsTextArea.setLineWrap(true);
        reportsTextArea.setWrapStyleWord(true);
        reportsTextArea.setRows(20);
        reportsTextArea.setColumns(50);
    }

    public void setAutoScrollLogCheckBoxMenuItem(boolean isSet) {
        this.autoScrollLogCheckBoxMenuItem.setSelected(isSet);
    }

    public void setShowSimulationCheckBoxMenuItem(boolean isSet) {
        this.showSimulationCheckBoxMenuItem.setSelected(isSet);
    }

    public void setTopBoxText(String text) {
        topBox.setText(text);
    }

    public void setStatusBarText(String text) {
        statusBar.setText(text);
    }

    public String getStatusBarText() {
        return statusBar.getText();
    }

    public void setStatusBarState(String text) {
        statusBarState.setText(text);
    }

    public String getStatusBarState() {
        return statusBarState.getText();
    }

    public void setStatusBarTime(String text) {
        statusBarTime.setText(text);
    }

    public void setReportText(String text) {
        reportsTextArea.setText((!"No reports to show".equals(text) ? ("Report " + (currentReportNumber + 1) + ":\n\n") : "") + text);
    }

    public void setCurrentReportNumber(int number) {
        currentReportNumber = number;
        reportNumberTextField.setText(String.valueOf(currentReportNumber + 1));
    }

    public void toggleStartAbortButton() {
        for (int i = 1; i < 10; i++) {
            gridworldTabPane.setEnabledAt(i, Shared.stm.kingsMoves || i < 6);
        }
        if (startAbortButton.getText().equals("START")) {
            startAbortButton.setText("ABORT");
            startAbortButton.setMnemonic('A');
            startAbortButton.setBackground(new Color(210, 4, 45));
        } else {
            startAbortButton.setText("START");
            startAbortButton.setMnemonic('S');
            startAbortButton.setBackground(new Color(34, 139, 34));
        }
    }

    public void saveFile() {
        if (stm.simulationRunning) {
            log("Simulation is running, cannot save configuration", "warning");
            Shared.messageFactory.setTitle("Error").setMessage("You cannot save a configuration while a simulation is running.").setModal(true).create();
            return;
        }
        try {
            Shared.stm.saveConfiguration(Shared.ios.saveFileDialog(this, "Save Configuration", ".txt", "JGridWorld Configuration File", false));
        }
        catch (IOException e) {
            Shared.messageFactory.setTitle("Save Error").setMessage("An error occurred while saving the configuration: " + e.getMessage()).setModal(true).create();
        }
        finally {
            setStatusBarText("Done");
            setStatusBarState("Ready");
        }
    }

    public void loadFile(String caller, Runnable onComplete) {
        if (stm.simulationRunning) {
            log("Simulation is running, cannot load configuration", "warning");
            Shared.messageFactory.setTitle("Error").setMessage("You cannot load a configuration while a simulation is running.").setModal(true).create();
            return;
        }
        if ("self".equals(caller) && Shared.stm.getNumOfReports() > 0) {
            if (Shared.preferenceManager.getPreferenceAsBoolean("noAskLoadConfiguration") || !Shared.promptFactory.setMessage("Are you sure you want to load a new configuration?<br><span style='color: red'>Unsaved data will be lost!</span>")
                                                                                                                        .setTitle("What about the current simulation?").setDNAA(true).setPreferenceKey("noAskLoadConfiguration").setModal(true).setDefaultOption("no")
                                                                                                                        .create().getAnswer()) {
                return;
            }
        }
        try {
            Shared.stm.loadConfiguration(Shared.ios.openFileDialog(this, "Open Configuration", "txt", "GridWorld Configuration File", false));
        }
        catch (IOException e) {
            Shared.messageFactory.setTitle("Load Error").setMessage("An error occurred while loading the configuration: " + e.getMessage()).setModal(true).create();
        }
        finally {
            setStatusBarText("Done");
            setStatusBarState("Ready");
            if (onComplete != null) {
                SwingUtilities.invokeLater(onComplete);
            }
        }
    }

    public void loadFile(String caller) {
        loadFile(caller, null);
    }

    public void enableTableGridlines() {
        for (int i = 0; i < gridworldTabPane.getTabCount() - 1; i++) {
            JTable table = (JTable) gridworldTabPane.getComponentAt(i);
            table.setShowGrid(true);
        }
    }

    
    public static void main(String[] args) {
      

        
        java.awt.EventQueue.invokeLater(() -> {
          
          
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                Shared.log("JGridWorld shutting down...");
                preferenceManager.onExit();
            }));
            Shared.log("JGridWorld starting up...\nRunning under " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ")\nHeap size: " + String.format("%,d", ((Runtime.getRuntime().maxMemory() / 1024) / 1024)) + " MB\nJava version: " + System.getProperty("java.version"));
            preferenceManager.onStartup();
            Shared.mainWindow.enableTableGridlines();
            Shared.mainWindow.setVisible(true);
          
            javax.swing.Timer clock = new javax.swing.Timer(1000, null);
            clock.setInitialDelay(0);
            clock.setRepeats(true);
            clock.addActionListener(e -> {
                Shared.setStatusBarTime(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("MMM dd/yyyy  HH:mm:ss")));
            });
            clock.start();
            Shared.log("Preparations done!");
        });
    }

  
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JCheckBoxMenuItem autoScrollLogCheckBoxMenuItem;
    private javax.swing.JTable averageValuesTable;
    private javax.swing.JButton clearAllReportsButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JTable downLeftValuesTable;
    private javax.swing.JTable downRightValuesTable;
    private javax.swing.JTable downValuesTable;
    private javax.swing.JMenuItem editGridWorldMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton goToReportButton;
    private javax.swing.JTabbedPane gridworldTabPane;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JTable leftValuesTable;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newSimMenuItem;
    private javax.swing.JMenuItem newWindowMenuItem;
    private javax.swing.JButton nextReportButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem preferencesMenuItem;
    private javax.swing.JButton previousReportButton;
    private javax.swing.JButton printReportButton;
    private javax.swing.JLabel reportNumberFieldLabel;
    private javax.swing.JTextField reportNumberTextField;
    private javax.swing.JScrollPane reportsScrollPane;
    private javax.swing.JPanel reportsTab;
    private javax.swing.JTextArea reportsTextArea;
    private javax.swing.JTable rightValuesTable;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JCheckBoxMenuItem showSimulationCheckBoxMenuItem;
    private javax.swing.JTextPane simLog;
    private javax.swing.JScrollPane simLogScrollPane;
    private javax.swing.JMenuItem simOptionsMenuItem;
    private javax.swing.JTable simTable;
    private javax.swing.JButton startAbortButton;
    private javax.swing.JMenuItem statisticsMenuItem;
    private javax.swing.JTextField statusBar;
    private javax.swing.JTextField statusBarState;
    private javax.swing.JTextField statusBarTime;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel topBox;
    private javax.swing.JTable upLeftValuesTable;
    private javax.swing.JTable upRightValuesTable;
    private javax.swing.JTable upValuesTable;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JButton voidReportButton;
  
    private int currentReportNumber = 0;
    private record LogEntry(String text, String logType) {}
    private final ArrayList<LogEntry> logHistory = new ArrayList<>();
    private SimOptionsWindow simOptionsWindow;
    private GridWorldEditor gridWorldEditor;
    private PreferencesWindow preferencesWindow;
    private StatisticsWindow statisticsWindow;
}
