
package com.nemu.jgridworld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;


public class PreferencesWindow extends JFrame {

    
    public PreferencesWindow() {
        initComponents();
      
        themeComboBox.setModel(new DefaultComboBoxModel<>(Shared.preferenceManager.getAvailableThemes()));
      
        configureSyncPopupMenu.add(preferredLightThemeLabel);
        for (String theme : Shared.preferenceManager.getAvailableThemes("light")) {
          
            JRadioButtonMenuItem themeButton = new JRadioButtonMenuItem(theme);
            themeButton.setSelected(Objects.equals(theme, Shared.preferenceManager.getPreference("uiPreferredLightTheme")));
            themeButton.putClientProperty("RadioButtonMenuItem.doNotCloseOnMouseClick", true);
            themeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    preferredLightTheme = ((JRadioButtonMenuItem) evt.getSource()).getText();
                }
            });
            preferredLightThemeButtonGroup.add(themeButton);
            configureSyncPopupMenu.add(themeButton);
        }
      
        configureSyncPopupMenu.add(configureSyncPopupMenuSeparator);
        configureSyncPopupMenu.add(preferredDarkThemeLabel);
        for (String theme : Shared.preferenceManager.getAvailableThemes("dark")) {
          
            JRadioButtonMenuItem themeButton = new JRadioButtonMenuItem(theme);
            themeButton.setSelected(Objects.equals(theme, Shared.preferenceManager.getPreference("uiPreferredDarkTheme")));
            themeButton.putClientProperty("RadioButtonMenuItem.doNotCloseOnMouseClick", true);
            themeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    preferredDarkTheme = ((JRadioButtonMenuItem) evt.getSource()).getText();
                }
            });
            preferredDarkThemeButtonGroup.add(themeButton);
            configureSyncPopupMenu.add(themeButton);
        }
        this.reloadAllValues();
        this.pack();
        this.setLocationRelativeTo(Shared.mainWindow);
        this.setVisible(true);
    }


    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        accentColorButtonGroup = new javax.swing.ButtonGroup();
        configureSyncPopupMenu = new javax.swing.JPopupMenu();
        preferredLightThemeLabel = new javax.swing.JMenuItem();
        configureSyncPopupMenuSeparator = new javax.swing.JPopupMenu.Separator();
        preferredDarkThemeLabel = new javax.swing.JMenuItem();
        preferredLightThemeButtonGroup = new javax.swing.ButtonGroup();
        preferredDarkThemeButtonGroup = new javax.swing.ButtonGroup();
        appearancePanel = new javax.swing.JPanel();
        themeLabel = new javax.swing.JLabel();
        themeComboBox = new javax.swing.JComboBox<>();
        useRoundedComponentsCheckBox = new javax.swing.JCheckBox();
        accentColorLabel = new javax.swing.JLabel();
        lightBlueAccentButton = new javax.swing.JToggleButton();
        darkBlueAccentButton = new javax.swing.JToggleButton();
        purpleAccentButton = new javax.swing.JToggleButton();
        redAccentButton = new javax.swing.JToggleButton();
        orangeAccentButton = new javax.swing.JToggleButton();
        yellowAccentButton = new javax.swing.JToggleButton();
        greenAccentButton = new javax.swing.JToggleButton();
        underlineAccessKeysCheckBox = new javax.swing.JCheckBox();
        syncThemeWithOsCheckBox = new javax.swing.JCheckBox();
        configureSyncButton = new javax.swing.JButton();
        themeDecidesAccentButton = new javax.swing.JToggleButton();
        accentColorNoteLabel = new javax.swing.JLabel();
        useCircularComponentsCheckBox = new javax.swing.JCheckBox();
        loggingPanel = new javax.swing.JPanel();
        clearLogButton = new javax.swing.JButton();
        saveLogButton = new javax.swing.JButton();
        autoscrollLogCheckBox = new javax.swing.JCheckBox();
        maxLogEntriesLabel = new javax.swing.JLabel();
        maxLogEntriesSpinner = new javax.swing.JSpinner();
        notificationPanel = new javax.swing.JPanel();
        resetPromptFlagsButton = new javax.swing.JButton();
        fileHandlingPanel = new javax.swing.JPanel();
        defaultConfigDirLabel = new javax.swing.JLabel();
        defaultConfigDirTextField = new javax.swing.JTextField();
        ignoreBadChecksumsCheckBox = new javax.swing.JCheckBox();
        defaultConfigDirBrowseButton = new javax.swing.JButton();
        startupBehaviorPanel = new javax.swing.JPanel();
        rememberWindowSizeCheckBox = new javax.swing.JCheckBox();
        autoLoadLastConfigCheckBox = new javax.swing.JCheckBox();
        defaultStartupTabLabel = new javax.swing.JLabel();
        defaultStartupTabComboBox = new javax.swing.JComboBox<>();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        funStuffPanel = new javax.swing.JPanel();
        windowEffectsCheckBox = new javax.swing.JCheckBox();
        enableAchievementsCheckBox = new javax.swing.JCheckBox();
        statusBarQuotesCheckBox = new javax.swing.JCheckBox();
        resetButton = new javax.swing.JButton();

        preferredLightThemeLabel.setText("For Light OS");
        preferredLightThemeLabel.setEnabled(false);
        preferredLightThemeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        configureSyncPopupMenu.add(preferredLightThemeLabel);
        configureSyncPopupMenu.add(configureSyncPopupMenuSeparator);

        preferredDarkThemeLabel.setText("For Dark OS");
        preferredDarkThemeLabel.setEnabled(false);
        preferredDarkThemeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        configureSyncPopupMenu.add(preferredDarkThemeLabel);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Preferences");
        setIconImage(Images.TITLE_BAR_ICON);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        appearancePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Appearance"));

        themeLabel.setDisplayedMnemonic('T');
        themeLabel.setText("Theme:");
        themeLabel.setToolTipText("");

        themeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Load Error" }));
        themeComboBox.setMaximumSize(new java.awt.Dimension(82, 20));

        useRoundedComponentsCheckBox.setMnemonic('R');
        useRoundedComponentsCheckBox.setText("Use rounded UI elements");
        useRoundedComponentsCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));
        useRoundedComponentsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useRoundedComponentsCheckBoxActionPerformed(evt);
            }
        });

        accentColorLabel.setDisplayedMnemonic('C');
        accentColorLabel.setLabelFor(lightBlueAccentButton);
        accentColorLabel.setText("Accent color:");

        accentColorButtonGroup.add(lightBlueAccentButton);
        lightBlueAccentButton.setIcon(Images.LIGHT_BLUE_ICON);
        lightBlueAccentButton.setBorderPainted(false);
        lightBlueAccentButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        lightBlueAccentButton.setPreferredSize(new java.awt.Dimension(16, 16));
        lightBlueAccentButton.setSelectedIcon(Images.LIGHT_BLUE_SELECTED);

        accentColorButtonGroup.add(darkBlueAccentButton);
        darkBlueAccentButton.setIcon(Images.DARK_BLUE_ICON);
        darkBlueAccentButton.setBorderPainted(false);
        darkBlueAccentButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        darkBlueAccentButton.setPreferredSize(new java.awt.Dimension(16, 16));
        darkBlueAccentButton.setSelectedIcon(Images.DARK_BLUE_SELECTED);

        accentColorButtonGroup.add(purpleAccentButton);
        purpleAccentButton.setIcon(Images.PURPLE_ICON);
        purpleAccentButton.setBorderPainted(false);
        purpleAccentButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        purpleAccentButton.setPreferredSize(new java.awt.Dimension(16, 16));
        purpleAccentButton.setSelectedIcon(Images.PURPLE_SELECTED);

        accentColorButtonGroup.add(redAccentButton);
        redAccentButton.setIcon(Images.RED_ICON);
        redAccentButton.setBorderPainted(false);
        redAccentButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        redAccentButton.setPreferredSize(new java.awt.Dimension(16, 16));
        redAccentButton.setSelectedIcon(Images.RED_SELECTED);

        accentColorButtonGroup.add(orangeAccentButton);
        orangeAccentButton.setIcon(Images.ORANGE_ICON);
        orangeAccentButton.setBorderPainted(false);
        orangeAccentButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        orangeAccentButton.setPreferredSize(new java.awt.Dimension(16, 16));
        orangeAccentButton.setSelectedIcon(Images.ORANGE_SELECTED);

        accentColorButtonGroup.add(yellowAccentButton);
        yellowAccentButton.setIcon(Images.YELLOW_ICON);
        yellowAccentButton.setBorderPainted(false);
        yellowAccentButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        yellowAccentButton.setPreferredSize(new java.awt.Dimension(16, 16));
        yellowAccentButton.setSelectedIcon(Images.YELLOW_SELECTED);

        accentColorButtonGroup.add(greenAccentButton);
        greenAccentButton.setIcon(Images.GREEN_ICON);
        greenAccentButton.setBorderPainted(false);
        greenAccentButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        greenAccentButton.setPreferredSize(new java.awt.Dimension(16, 16));
        greenAccentButton.setSelectedIcon(Images.GREEN_SELECTED);

        underlineAccessKeysCheckBox.setMnemonic('U');
        underlineAccessKeysCheckBox.setText("Always underline access keys (*)");
        underlineAccessKeysCheckBox.setToolTipText("<html>When checked, underlines the access key (mnemonic) used for keyboard navigation (e.g., Alt+F for File menu).<br>If this is unchecked, you can hold Alt to underline access keys instead.</html>");
        underlineAccessKeysCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));

        syncThemeWithOsCheckBox.setMnemonic('Y');
        syncThemeWithOsCheckBox.setText("Sync with OS");
        syncThemeWithOsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syncThemeWithOsCheckBoxActionPerformed(evt);
            }
        });

        configureSyncButton.setText("Configure...");
        configureSyncButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureSyncButtonActionPerformed(evt);
            }
        });

        accentColorButtonGroup.add(themeDecidesAccentButton);
        themeDecidesAccentButton.setIcon(Images.THEME_DECIDES);
        themeDecidesAccentButton.setSelected(true);
        themeDecidesAccentButton.setToolTipText("Allow the theme to decide the accent color.");
        themeDecidesAccentButton.setBorderPainted(false);
        themeDecidesAccentButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        themeDecidesAccentButton.setPreferredSize(new java.awt.Dimension(16, 16));
        themeDecidesAccentButton.setSelectedIcon(Images.THEME_DECIDES_SELECTED);

        accentColorNoteLabel.setText("Note: Some themes may not respect custom accent colors");

        useCircularComponentsCheckBox.setMnemonic('X');
        useCircularComponentsCheckBox.setText("Extra rounded (circular)");
        useCircularComponentsCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));

        javax.swing.GroupLayout appearancePanelLayout = new javax.swing.GroupLayout(appearancePanel);
        appearancePanel.setLayout(appearancePanelLayout);
        appearancePanelLayout.setHorizontalGroup(
            appearancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appearancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(appearancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appearancePanelLayout.createSequentialGroup()
                        .addComponent(accentColorLabel)
                        .addGap(12, 12, 12)
                        .addComponent(themeDecidesAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lightBlueAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(darkBlueAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(purpleAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(redAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(orangeAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yellowAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(greenAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(appearancePanelLayout.createSequentialGroup()
                        .addComponent(themeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(themeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(syncThemeWithOsCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(configureSyncButton))
                    .addComponent(underlineAccessKeysCheckBox)
                    .addGroup(appearancePanelLayout.createSequentialGroup()
                        .addComponent(useRoundedComponentsCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(useCircularComponentsCheckBox))
                    .addComponent(accentColorNoteLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        appearancePanelLayout.setVerticalGroup(
            appearancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appearancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(appearancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themeLabel)
                    .addComponent(themeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(syncThemeWithOsCheckBox)
                    .addComponent(configureSyncButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(appearancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accentColorLabel)
                    .addComponent(lightBlueAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(darkBlueAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purpleAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(redAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orangeAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yellowAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greenAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(themeDecidesAccentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accentColorNoteLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(appearancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useRoundedComponentsCheckBox)
                    .addComponent(useCircularComponentsCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(underlineAccessKeysCheckBox)
                .addContainerGap())
        );

        loggingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Logging"));

        clearLogButton.setMnemonic('L');
        clearLogButton.setText("Clear Log");
        clearLogButton.setDisplayedMnemonicIndex(6);
        clearLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogButtonActionPerformed(evt);
            }
        });

        saveLogButton.setMnemonic('S');
        saveLogButton.setText("Save Log to File...");
        saveLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveLogButtonActionPerformed(evt);
            }
        });

        autoscrollLogCheckBox.setMnemonic('G');
        autoscrollLogCheckBox.setSelected(true);
        autoscrollLogCheckBox.setText("Auto-scroll log");
        autoscrollLogCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));

        maxLogEntriesLabel.setDisplayedMnemonic('B');
        maxLogEntriesLabel.setLabelFor(maxLogEntriesSpinner);
        maxLogEntriesLabel.setText("Max number of log entries:");

        maxLogEntriesSpinner.setModel(new javax.swing.SpinnerNumberModel(9999, 50, 9999, 1));

        javax.swing.GroupLayout loggingPanelLayout = new javax.swing.GroupLayout(loggingPanel);
        loggingPanel.setLayout(loggingPanelLayout);
        loggingPanelLayout.setHorizontalGroup(
            loggingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loggingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggingPanelLayout.createSequentialGroup()
                        .addComponent(autoscrollLogCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveLogButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggingPanelLayout.createSequentialGroup()
                        .addComponent(maxLogEntriesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxLogEntriesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearLogButton)))
                .addContainerGap())
        );
        loggingPanelLayout.setVerticalGroup(
            loggingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loggingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxLogEntriesLabel)
                    .addComponent(maxLogEntriesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearLogButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loggingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveLogButton)
                    .addComponent(autoscrollLogCheckBox))
                .addContainerGap())
        );

        notificationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Notifications"));

        resetPromptFlagsButton.setMnemonic('F');
        resetPromptFlagsButton.setText("Reset All \"Do Not Show/Ask This Again\" Flags");
        resetPromptFlagsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPromptFlagsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout notificationPanelLayout = new javax.swing.GroupLayout(notificationPanel);
        notificationPanel.setLayout(notificationPanelLayout);
        notificationPanelLayout.setHorizontalGroup(
            notificationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notificationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resetPromptFlagsButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        notificationPanelLayout.setVerticalGroup(
            notificationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notificationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resetPromptFlagsButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileHandlingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("File Handling"));

        defaultConfigDirLabel.setDisplayedMnemonic('D');
        defaultConfigDirLabel.setLabelFor(defaultConfigDirBrowseButton);
        defaultConfigDirLabel.setText("Default config directory:");

        defaultConfigDirTextField.setEditable(false);

        ignoreBadChecksumsCheckBox.setMnemonic('M');
        ignoreBadChecksumsCheckBox.setText("Ignore invalid checksums (not recommended*)");
        ignoreBadChecksumsCheckBox.setToolTipText("<html>Enabling this may cause data corruption or unexpected errors.<br>Don't enable this unless you're sure you know what you're doing.</html>");
        ignoreBadChecksumsCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));

        defaultConfigDirBrowseButton.setText("...");
        defaultConfigDirBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultConfigDirBrowseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fileHandlingPanelLayout = new javax.swing.GroupLayout(fileHandlingPanel);
        fileHandlingPanel.setLayout(fileHandlingPanelLayout);
        fileHandlingPanelLayout.setHorizontalGroup(
            fileHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileHandlingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fileHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fileHandlingPanelLayout.createSequentialGroup()
                        .addComponent(defaultConfigDirLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(defaultConfigDirTextField)
                        .addGap(0, 0, 0)
                        .addComponent(defaultConfigDirBrowseButton))
                    .addGroup(fileHandlingPanelLayout.createSequentialGroup()
                        .addComponent(ignoreBadChecksumsCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        fileHandlingPanelLayout.setVerticalGroup(
            fileHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fileHandlingPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(fileHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defaultConfigDirLabel)
                    .addComponent(defaultConfigDirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultConfigDirBrowseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ignoreBadChecksumsCheckBox)
                .addContainerGap())
        );

        startupBehaviorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Startup & Behavior"));

        rememberWindowSizeCheckBox.setMnemonic('W');
        rememberWindowSizeCheckBox.setSelected(true);
        rememberWindowSizeCheckBox.setText("Remember window size/position");
        rememberWindowSizeCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));

        autoLoadLastConfigCheckBox.setMnemonic('I');
        autoLoadLastConfigCheckBox.setText("Auto-load last configuration");
        autoLoadLastConfigCheckBox.setDisplayedMnemonicIndex(25);
        autoLoadLastConfigCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));

        defaultStartupTabLabel.setDisplayedMnemonic('E');
        defaultStartupTabLabel.setLabelFor(defaultStartupTabComboBox);
        defaultStartupTabLabel.setText("Default tab on startup:");

        defaultStartupTabComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GridWorld", "Average Values", "Up Values", "Down Values", "Left Values", "Right Values", "Up-Left Values", "Down-Left Values", "Up-Right Values", "Down-Right Values", "Reports" }));

        javax.swing.GroupLayout startupBehaviorPanelLayout = new javax.swing.GroupLayout(startupBehaviorPanel);
        startupBehaviorPanel.setLayout(startupBehaviorPanelLayout);
        startupBehaviorPanelLayout.setHorizontalGroup(
            startupBehaviorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startupBehaviorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(startupBehaviorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rememberWindowSizeCheckBox)
                    .addComponent(autoLoadLastConfigCheckBox)
                    .addGroup(startupBehaviorPanelLayout.createSequentialGroup()
                        .addComponent(defaultStartupTabLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(defaultStartupTabComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        startupBehaviorPanelLayout.setVerticalGroup(
            startupBehaviorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startupBehaviorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rememberWindowSizeCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoLoadLastConfigCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(startupBehaviorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defaultStartupTabLabel)
                    .addComponent(defaultStartupTabComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setMnemonic('O');
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        applyButton.setMnemonic('A');
        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        funStuffPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Fun Stuff"));

        windowEffectsCheckBox.setMnemonic('N');
        windowEffectsCheckBox.setText("Enable window effects (*)");
        windowEffectsCheckBox.setToolTipText("You'll have to enable this to see what it does!");
        windowEffectsCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));

        enableAchievementsCheckBox.setMnemonic('V');
        enableAchievementsCheckBox.setText("Enable achievements");

        statusBarQuotesCheckBox.setMnemonic('Q');
        statusBarQuotesCheckBox.setSelected(true);
        statusBarQuotesCheckBox.setText("Enable status bar quotes");
        statusBarQuotesCheckBox.setMargin(new java.awt.Insets(2, 0, 2, 2));

        javax.swing.GroupLayout funStuffPanelLayout = new javax.swing.GroupLayout(funStuffPanel);
        funStuffPanel.setLayout(funStuffPanelLayout);
        funStuffPanelLayout.setHorizontalGroup(
            funStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(funStuffPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(funStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(funStuffPanelLayout.createSequentialGroup()
                        .addComponent(windowEffectsCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(enableAchievementsCheckBox))
                    .addComponent(statusBarQuotesCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        funStuffPanelLayout.setVerticalGroup(
            funStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(funStuffPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(funStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(windowEffectsCheckBox)
                    .addComponent(enableAchievementsCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusBarQuotesCheckBox)
                .addContainerGap())
        );

        resetButton.setText("Reset");
        resetButton.setToolTipText("Reset all preferences to defaults");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notificationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fileHandlingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton))
                    .addComponent(appearancePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startupBehaviorPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loggingPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(funStuffPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appearancePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startupBehaviorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileHandlingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notificationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loggingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(funStuffPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(cancelButton)
                    .addComponent(okButton)
                    .addComponent(resetButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void resetPromptFlagsButtonActionPerformed(ActionEvent evt) {
        if (Shared.promptFactory.setParent(this).setTitle("Reset Prompt Flags").setMessage("Are you sure you want to reset all \"Do Not Show/Ask This Again\" flags?").setModal(true).setDefaultOption("No").create().getAnswer()) {
            Shared.preferenceManager.resetPromptFlags();
            Shared.messageFactory.setParent(this).setTitle("Prompt Flags Reset").setMessage("All \"Do Not Show/Ask This Again\" flags have been reset.").setModal(true).create();
        }
    }

    private void defaultConfigDirBrowseButtonActionPerformed(ActionEvent evt) {
        String selectedDir = Objects.requireNonNullElse(Shared.ios.setDirectoryDialog(this, "Select Default Config Directory", "Folders Only"), defaultConfigDirTextField.getText());
        defaultConfigDirTextField.setText(selectedDir);
    }

    private void clearLogButtonActionPerformed(ActionEvent evt) {
        if (Shared.promptFactory.setParent(this).setTitle("Clear Log").setMessage("Are you sure you want to clear the log?").setModal(true).setDefaultOption("No").create().getAnswer()) {
            Shared.clearLog();
        }
    }

    private void saveLogButtonActionPerformed(ActionEvent evt) {
        try {
            File fileToWrite = Shared.ios.saveFileDialog(Shared.mainWindow, "Save Log", ".log", "Log Files (.log)", false);
            if (fileToWrite == null) {
                return;
            }
            FileWriter fw = new FileWriter(fileToWrite);
            fw.write(Shared.getLogText());
            fw.close();
            Shared.messageFactory.setParent(this).setTitle("Log Saved").setMessage("Log saved successfully.").setModal(true).create();
        } catch (Exception e) {
            Shared.messageFactory.setParent(this).setTitle("Error Saving Log").setMessage("An error occurred while saving the log: " + e.getMessage()).setModal(true).create();
        }

    }

    private void resetButtonActionPerformed(ActionEvent evt) {
        if (Shared.promptFactory.setParent(this).setTitle("Reset Preferences").setMessage("Are you sure you want to reset all preferences to defaults?").setModal(true).setDefaultOption("No").create().getAnswer()) {
            Shared.preferenceManager.resetPreferences();
            reloadAllValues();
            Shared.messageFactory.setParent(this).setTitle("Preferences Reset").setMessage("All preferences have been reset to defaults.").setModal(true).create();
        }
    }

    private void okButtonActionPerformed(ActionEvent evt) {
        savePreferences();
        this.dispose();
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        if (haveSettingsChanged() && !Shared.promptFactory.setParent(this).setMessage("You have unsaved changes.<br>Are you sure you want to cancel?").setTitle("Unsaved Changes").setModal(true).create().getAnswer()) {
            return;
        }
        this.dispose();
    }

    private void applyButtonActionPerformed(ActionEvent evt) {
        savePreferences();
    }

    private void syncThemeWithOsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        themeComboBox.setEnabled(!syncThemeWithOsCheckBox.isSelected());
        configureSyncButton.setEnabled(syncThemeWithOsCheckBox.isSelected());
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        cancelButtonActionPerformed(null);
    }

    private void configureSyncButtonActionPerformed(java.awt.event.ActionEvent evt) {
      
        configureSyncPopupMenu.show(configureSyncButton, 0, configureSyncButton.getHeight());
    }

    private void useRoundedComponentsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        useCircularComponentsCheckBox.setEnabled(useRoundedComponentsCheckBox.isSelected());
    }

  
    private boolean haveSettingsChanged() {
        return !Objects.equals(Shared.preferenceManager.getAvailableThemes()[themeComboBox.getSelectedIndex()], Shared.preferenceManager.getPreference("uiPreferredTheme")) ||
                !(Shared.preferenceManager.getPreference("uiPreferredLightTheme")).equals(preferredLightTheme) ||
                !(Shared.preferenceManager.getPreference("uiPreferredDarkTheme")).equals(preferredDarkTheme) ||
                syncThemeWithOsCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("uiSyncThemeWithOS") ||
                themeDecidesAccentButton.isSelected() != Shared.preferenceManager.getPreference("uiPreferredAccentColor").equals("theme") ||
                lightBlueAccentButton.isSelected() != Shared.preferenceManager.getPreference("uiPreferredAccentColor").equals("lightBlue") ||
                darkBlueAccentButton.isSelected() != Shared.preferenceManager.getPreference("uiPreferredAccentColor").equals("darkBlue") ||
                purpleAccentButton.isSelected() != Shared.preferenceManager.getPreference("uiPreferredAccentColor").equals("purple") ||
                redAccentButton.isSelected() != Shared.preferenceManager.getPreference("uiPreferredAccentColor").equals("red") ||
                orangeAccentButton.isSelected() != Shared.preferenceManager.getPreference("uiPreferredAccentColor").equals("orange") ||
                yellowAccentButton.isSelected() != Shared.preferenceManager.getPreference("uiPreferredAccentColor").equals("yellow") ||
                greenAccentButton.isSelected() != Shared.preferenceManager.getPreference("uiPreferredAccentColor").equals("green") ||
                useRoundedComponentsCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("uiPrefersRoundedComponents") ||
                useCircularComponentsCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("uiPrefersCircularComponents") ||
                underlineAccessKeysCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("uiAlwaysUnderlineAccessKeys") ||
                rememberWindowSizeCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("uiRememberWindowSizeLocation") ||
                autoLoadLastConfigCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("fileAutoLoadLastConfiguration") ||
                defaultStartupTabComboBox.getSelectedIndex() != Shared.preferenceManager.getPreferenceAsInt("uiDefaultStartupTab") ||
                !defaultConfigDirTextField.getText().equals(Shared.preferenceManager.getPreference("fileDefaultConfigDirectory")) ||
                ignoreBadChecksumsCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("fileIgnoreBadChecksums") ||
                (int) maxLogEntriesSpinner.getValue() != Shared.preferenceManager.getPreferenceAsInt("uiMaxLogSize") ||
                autoscrollLogCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("uiAutoScrollLog") ||
                windowEffectsCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("funStuffEnableWindowEffects") ||
                enableAchievementsCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("funStuffEnableAchievements") ||
                statusBarQuotesCheckBox.isSelected() != Shared.preferenceManager.getPreferenceAsBoolean("funStuffEnableStatusBarQuotes");
    }

    public void savePreferences() {
        if (!haveSettingsChanged()) return;
        Shared.preferenceManager.setPreference("uiPreferredTheme", Shared.preferenceManager.getAvailableThemes()[themeComboBox.getSelectedIndex()]);
        Shared.preferenceManager.setPreference("uiPreferredLightTheme", preferredLightTheme);
        Shared.preferenceManager.setPreference("uiPreferredDarkTheme", preferredDarkTheme);
        Shared.preferenceManager.setPreference("uiSyncThemeWithOS", syncThemeWithOsCheckBox.isSelected());
        Shared.preferenceManager.setPreference("uiPreferredAccentColor", themeDecidesAccentButton.isSelected() ? "theme" : lightBlueAccentButton.isSelected() ? "lightBlue" : darkBlueAccentButton.isSelected() ? "darkBlue" : purpleAccentButton.isSelected() ? "purple" : redAccentButton.isSelected() ? "red" : orangeAccentButton.isSelected() ? "orange" : yellowAccentButton.isSelected() ? "yellow" : greenAccentButton.isSelected() ? "green" : "");
        Shared.preferenceManager.setPreference("uiPrefersRoundedComponents", useRoundedComponentsCheckBox.isSelected());
        Shared.preferenceManager.setPreference("uiPrefersCircularComponents", useCircularComponentsCheckBox.isSelected());
        Shared.preferenceManager.setPreference("uiAlwaysUnderlineAccessKeys", underlineAccessKeysCheckBox.isSelected());
        Shared.preferenceManager.setPreference("uiRememberWindowSizeLocation", rememberWindowSizeCheckBox.isSelected());
        Shared.preferenceManager.setPreference("fileAutoLoadLastConfiguration", autoLoadLastConfigCheckBox.isSelected());
        Shared.preferenceManager.setPreference("uiDefaultStartupTab", defaultStartupTabComboBox.getSelectedIndex());
        Shared.preferenceManager.setPreference("fileDefaultConfigDirectory", defaultConfigDirTextField.getText());
        Shared.preferenceManager.setPreference("fileIgnoreBadChecksums", ignoreBadChecksumsCheckBox.isSelected());
        Shared.preferenceManager.setPreference("uiMaxLogSize", (int) maxLogEntriesSpinner.getValue());
        Shared.preferenceManager.setPreference("uiAutoScrollLog", autoscrollLogCheckBox.isSelected());
        Shared.preferenceManager.setPreference("funStuffEnableWindowEffects", windowEffectsCheckBox.isSelected());
        Shared.preferenceManager.setPreference("funStuffEnableAchievements", enableAchievementsCheckBox.isSelected());
        Shared.preferenceManager.setPreference("funStuffEnableStatusBarQuotes", statusBarQuotesCheckBox.isSelected());
        Shared.preferenceManager.applyPreferences();
        Shared.log("Preferences saved!", "info");
        SwingUtilities.updateComponentTreeUI(configureSyncPopupMenu);
        this.toFront();
    }

    private void reloadAllValues() {
        themeComboBox.setSelectedIndex(List.of(Shared.preferenceManager.getAvailableThemes()).indexOf(Shared.preferenceManager.getPreference("uiPreferredTheme")));
        syncThemeWithOsCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("uiSyncThemeWithOS"));
        themeComboBox.setEnabled(!syncThemeWithOsCheckBox.isSelected());
        configureSyncButton.setEnabled(syncThemeWithOsCheckBox.isSelected());
        switch (Shared.preferenceManager.getPreference("uiPreferredAccentColor")) {
            case "theme" -> themeDecidesAccentButton.setSelected(true);
            case "lightBlue" -> lightBlueAccentButton.setSelected(true);
            case "darkBlue" -> darkBlueAccentButton.setSelected(true);
            case "purple" -> purpleAccentButton.setSelected(true);
            case "red" -> redAccentButton.setSelected(true);
            case "orange" -> orangeAccentButton.setSelected(true);
            case "yellow" -> yellowAccentButton.setSelected(true);
            case "green" -> greenAccentButton.setSelected(true);
            default -> {
                Shared.log("Unexpected accent color value in preferences: " + Shared.preferenceManager.getPreference("uiPreferredAccentColor"), "error");
                lightBlueAccentButton.setSelected(true);
            }
        }
        useRoundedComponentsCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("uiPrefersRoundedComponents"));
        useCircularComponentsCheckBox.setEnabled(useRoundedComponentsCheckBox.isSelected());
        useCircularComponentsCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("uiPrefersCircularComponents"));
        underlineAccessKeysCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("uiAlwaysUnderlineAccessKeys"));
        rememberWindowSizeCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("uiRememberWindowSizeLocation"));
        autoLoadLastConfigCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("fileAutoLoadLastConfiguration"));
        defaultStartupTabComboBox.setSelectedIndex(Shared.preferenceManager.getPreferenceAsInt("uiDefaultStartupTab"));
        defaultConfigDirTextField.setText(Shared.preferenceManager.getPreference("fileDefaultConfigDirectory"));
        ignoreBadChecksumsCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("fileIgnoreBadChecksums"));
        maxLogEntriesSpinner.setValue(Shared.preferenceManager.getPreferenceAsInt("uiMaxLogSize"));
        autoscrollLogCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("uiAutoScrollLog"));
        windowEffectsCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("funStuffEnableWindowEffects"));
        enableAchievementsCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("funStuffEnableAchievements"));
        statusBarQuotesCheckBox.setSelected(Shared.preferenceManager.getPreferenceAsBoolean("funStuffEnableStatusBarQuotes"));
        themeComboBox.setEnabled(!syncThemeWithOsCheckBox.isSelected());
    }



  
    private javax.swing.ButtonGroup accentColorButtonGroup;
    private javax.swing.JLabel accentColorLabel;
    private javax.swing.JLabel accentColorNoteLabel;
    private javax.swing.JPanel appearancePanel;
    private javax.swing.JButton applyButton;
    private javax.swing.JCheckBox autoLoadLastConfigCheckBox;
    private javax.swing.JCheckBox autoscrollLogCheckBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearLogButton;
    private javax.swing.JButton configureSyncButton;
    private javax.swing.JPopupMenu configureSyncPopupMenu;
    private javax.swing.JPopupMenu.Separator configureSyncPopupMenuSeparator;
    private javax.swing.JToggleButton darkBlueAccentButton;
    private javax.swing.JButton defaultConfigDirBrowseButton;
    private javax.swing.JLabel defaultConfigDirLabel;
    private javax.swing.JTextField defaultConfigDirTextField;
    private javax.swing.JComboBox<String> defaultStartupTabComboBox;
    private javax.swing.JLabel defaultStartupTabLabel;
    private javax.swing.JCheckBox enableAchievementsCheckBox;
    private javax.swing.JPanel fileHandlingPanel;
    private javax.swing.JPanel funStuffPanel;
    private javax.swing.JToggleButton greenAccentButton;
    private javax.swing.JCheckBox ignoreBadChecksumsCheckBox;
    private javax.swing.JToggleButton lightBlueAccentButton;
    private javax.swing.JPanel loggingPanel;
    private javax.swing.JLabel maxLogEntriesLabel;
    private javax.swing.JSpinner maxLogEntriesSpinner;
    private javax.swing.JPanel notificationPanel;
    private javax.swing.JButton okButton;
    private javax.swing.JToggleButton orangeAccentButton;
    private javax.swing.ButtonGroup preferredDarkThemeButtonGroup;
    private javax.swing.JMenuItem preferredDarkThemeLabel;
    private javax.swing.ButtonGroup preferredLightThemeButtonGroup;
    private javax.swing.JMenuItem preferredLightThemeLabel;
    private javax.swing.JToggleButton purpleAccentButton;
    private javax.swing.JToggleButton redAccentButton;
    private javax.swing.JCheckBox rememberWindowSizeCheckBox;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton resetPromptFlagsButton;
    private javax.swing.JButton saveLogButton;
    private javax.swing.JPanel startupBehaviorPanel;
    private javax.swing.JCheckBox statusBarQuotesCheckBox;
    private javax.swing.JCheckBox syncThemeWithOsCheckBox;
    private javax.swing.JComboBox<String> themeComboBox;
    private javax.swing.JToggleButton themeDecidesAccentButton;
    private javax.swing.JLabel themeLabel;
    private javax.swing.JCheckBox underlineAccessKeysCheckBox;
    private javax.swing.JCheckBox useCircularComponentsCheckBox;
    private javax.swing.JCheckBox useRoundedComponentsCheckBox;
    private javax.swing.JCheckBox windowEffectsCheckBox;
    private javax.swing.JToggleButton yellowAccentButton;
  
    private String preferredLightTheme = Shared.preferenceManager.getPreference("uiPreferredLightTheme");
    private String preferredDarkTheme = Shared.preferenceManager.getPreference("uiPreferredDarkTheme");
}
