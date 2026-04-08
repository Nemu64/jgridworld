
package com.nemu.jgridworld;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class SimOptionsWindow extends javax.swing.JFrame implements PropertyChangeListener {

    
    public SimOptionsWindow() {
        initComponents();
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(explorationProbabilitySpinner, "0%");
        explorationProbabilitySpinner.setEditor(editor);
        editor = new JSpinner.NumberEditor(learningRateSpinner, "0%");
        learningRateSpinner.setEditor(editor);
        editor = new JSpinner.NumberEditor(discountFactorSpinner, "0%");
        discountFactorSpinner.setEditor(editor);
        this.updateValues();
        Shared.stm.addPropertyChangeListener(this);
        this.disableUnavailableOptions(!Shared.stm.simulationRunning);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(Shared.mainWindow);
        this.setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        moveTypeButtonGroup = new javax.swing.ButtonGroup();
        windowContents = new javax.swing.JPanel();
        movementPanel = new javax.swing.JPanel();
        cardinalRadioButton = new javax.swing.JRadioButton();
        kingRadioButton = new javax.swing.JRadioButton();
        numOfTrialsLabel = new javax.swing.JLabel();
        numOfMovesLabel = new javax.swing.JLabel();
        numOfTrialsSpinner = new javax.swing.JSpinner();
        numOfMovesSpinner = new javax.swing.JSpinner();
        numOfTrialsResetButton = new javax.swing.JButton();
        numOfMovesResetButton = new javax.swing.JButton();
        cardinalIcon = new javax.swing.JLabel();
        kingIcon = new javax.swing.JLabel();
        outOfBoundsRuleLabel = new javax.swing.JLabel();
        outOfBoundsRuleComboBox = new javax.swing.JComboBox<>();
        windLabel = new javax.swing.JLabel();
        windComboBox = new javax.swing.JComboBox<>();
        speedLabel = new javax.swing.JLabel();
        speedComboBox = new javax.swing.JComboBox<>();
        learningParametersPanel = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        rewardValuePanel = new javax.swing.JPanel();
        rewardValueResetButton = new javax.swing.JButton();
        rewardValueSpinner = new javax.swing.JSpinner();
        rewardValueLabel = new javax.swing.JLabel();
        explorationProbabilityPanel = new javax.swing.JPanel();
        explorationProbabilityResetButton = new javax.swing.JButton();
        explorationProbabilityLabel = new javax.swing.JLabel();
        explorationProbabilitySpinner = new javax.swing.JSpinner();
        decayingProbabilityPanel = new javax.swing.JPanel();
        decayingProbabilityLabel = new javax.swing.JLabel();
        decayingProbabilityCheckBox = new javax.swing.JCheckBox();
        discountFactorPanel = new javax.swing.JPanel();
        discountFactorResetButton = new javax.swing.JButton();
        discountFactorLabel = new javax.swing.JLabel();
        discountFactorSpinner = new javax.swing.JSpinner();
        learningRatePanel = new javax.swing.JPanel();
        learningRateResetButton = new javax.swing.JButton();
        learningRateSpinner = new javax.swing.JSpinner();
        learningRateLabel = new javax.swing.JLabel();
        saveLoadPanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Simulation Options");
        setIconImage(Images.TITLE_BAR_ICON);
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        movementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Movement"));

        moveTypeButtonGroup.add(cardinalRadioButton);
        cardinalRadioButton.setMnemonic('C');
        cardinalRadioButton.setSelected(true);
        cardinalRadioButton.setText("Cardinal");

        moveTypeButtonGroup.add(kingRadioButton);
        kingRadioButton.setMnemonic('K');
        kingRadioButton.setText("King");

        numOfTrialsLabel.setDisplayedMnemonic('T');
        numOfTrialsLabel.setLabelFor(numOfTrialsSpinner);
        numOfTrialsLabel.setText("Number of trials:");

        numOfMovesLabel.setDisplayedMnemonic('M');
        numOfMovesLabel.setLabelFor(numOfMovesSpinner);
        numOfMovesLabel.setText("Number of moves:");

        numOfTrialsSpinner.setModel(new javax.swing.SpinnerNumberModel(100, 1, 1000000, 1));

        numOfMovesSpinner.setModel(new javax.swing.SpinnerNumberModel(100, 1, 999, 1));

        numOfTrialsResetButton.setText("↺");
        numOfTrialsResetButton.setToolTipText("Reset number of trials");
        numOfTrialsResetButton.setFocusable(false);
        numOfTrialsResetButton.setRequestFocusEnabled(false);
        numOfTrialsResetButton.setRolloverEnabled(false);
        numOfTrialsResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numOfTrialsResetButtonActionPerformed(evt);
            }
        });

        numOfMovesResetButton.setText("↺");
        numOfMovesResetButton.setToolTipText("Reset number of moves");
        numOfMovesResetButton.setFocusable(false);
        numOfMovesResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numOfMovesResetButtonActionPerformed(evt);
            }
        });

        cardinalIcon.setIcon(Images.CARDINAL_ARROWS);
        cardinalIcon.setLabelFor(cardinalRadioButton);

        kingIcon.setIcon(Images.KING_ARROWS);
        kingIcon.setLabelFor(kingRadioButton);

        outOfBoundsRuleLabel.setDisplayedMnemonic('W');
        outOfBoundsRuleLabel.setLabelFor(outOfBoundsRuleComboBox);
        outOfBoundsRuleLabel.setText("When the agent moves out of bounds, the following rule applies:");

        outOfBoundsRuleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reset move (default)", "End trial (counts as trial failure)", "Go to next move without taking action", "Wrap to other side" }));

        windLabel.setDisplayedMnemonic('N');
        windLabel.setLabelFor(windComboBox);
        windLabel.setText("Wind:");

        windComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disabled (default)", "Up", "Down" }));

        speedLabel.setDisplayedMnemonic('S');
        speedLabel.setLabelFor(speedComboBox);
        speedLabel.setText("Speed:");

        speedComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Very Slow", "Slow", "Medium (default)", "Fast", "Faster than light" }));

        javax.swing.GroupLayout movementPanelLayout = new javax.swing.GroupLayout(movementPanel);
        movementPanel.setLayout(movementPanelLayout);
        movementPanelLayout.setHorizontalGroup(
            movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movementPanelLayout.createSequentialGroup()
                        .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(movementPanelLayout.createSequentialGroup()
                                .addComponent(cardinalRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardinalIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(movementPanelLayout.createSequentialGroup()
                                .addComponent(kingRadioButton)
                                .addGap(25, 25, 25)
                                .addComponent(kingIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numOfMovesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numOfTrialsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numOfTrialsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numOfMovesSpinner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numOfTrialsResetButton)
                            .addComponent(numOfMovesResetButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(outOfBoundsRuleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(outOfBoundsRuleLabel)
                    .addGroup(movementPanelLayout.createSequentialGroup()
                        .addComponent(windLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(windComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(speedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        movementPanelLayout.setVerticalGroup(
            movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cardinalRadioButton)
                        .addComponent(numOfTrialsLabel)
                        .addComponent(numOfTrialsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(numOfTrialsResetButton))
                    .addComponent(cardinalIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kingRadioButton)
                        .addComponent(numOfMovesLabel)
                        .addComponent(numOfMovesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(numOfMovesResetButton))
                    .addComponent(kingIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outOfBoundsRuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outOfBoundsRuleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(windLabel)
                    .addComponent(windComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(speedLabel)
                    .addComponent(speedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        learningParametersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Learning Parameters"));

        helpButton.setMnemonic('H');
        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        rewardValueResetButton.setText("↺");
        rewardValueResetButton.setToolTipText("Reset default reward value");
        rewardValueResetButton.setFocusable(false);
        rewardValueResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rewardValueResetButtonActionPerformed(evt);
            }
        });

        rewardValueSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, -99.0d, 99.0d, 0.1d));

        rewardValueLabel.setDisplayedMnemonic('R');
        rewardValueLabel.setLabelFor(rewardValueSpinner);
        rewardValueLabel.setText("Default reward value:");

        javax.swing.GroupLayout rewardValuePanelLayout = new javax.swing.GroupLayout(rewardValuePanel);
        rewardValuePanel.setLayout(rewardValuePanelLayout);
        rewardValuePanelLayout.setHorizontalGroup(
            rewardValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rewardValuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rewardValueLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rewardValueSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rewardValueResetButton)
                .addContainerGap())
        );
        rewardValuePanelLayout.setVerticalGroup(
            rewardValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rewardValuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rewardValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rewardValueLabel)
                    .addComponent(rewardValueSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rewardValueResetButton))
                .addContainerGap())
        );

        explorationProbabilityResetButton.setText("↺");
        explorationProbabilityResetButton.setToolTipText("Reset exploration probability");
        explorationProbabilityResetButton.setFocusable(false);
        explorationProbabilityResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                explorationProbabilityResetButtonActionPerformed(evt);
            }
        });

        explorationProbabilityLabel.setDisplayedMnemonic('P');
        explorationProbabilityLabel.setLabelFor(explorationProbabilitySpinner);
        explorationProbabilityLabel.setText("Exploration probability:");

        explorationProbabilitySpinner.setModel(new javax.swing.SpinnerNumberModel(0.1d, 0.0d, 1.0d, 0.05d));

        javax.swing.GroupLayout explorationProbabilityPanelLayout = new javax.swing.GroupLayout(explorationProbabilityPanel);
        explorationProbabilityPanel.setLayout(explorationProbabilityPanelLayout);
        explorationProbabilityPanelLayout.setHorizontalGroup(
            explorationProbabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(explorationProbabilityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(explorationProbabilityLabel)
                .addGap(18, 18, 18)
                .addComponent(explorationProbabilitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(explorationProbabilityResetButton)
                .addGap(6, 6, 6))
        );
        explorationProbabilityPanelLayout.setVerticalGroup(
            explorationProbabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(explorationProbabilityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(explorationProbabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(explorationProbabilityLabel)
                    .addComponent(explorationProbabilitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(explorationProbabilityResetButton))
                .addContainerGap())
        );

        decayingProbabilityLabel.setDisplayedMnemonic('D');
        decayingProbabilityLabel.setLabelFor(decayingProbabilityCheckBox);
        decayingProbabilityLabel.setText("Decaying probability:");

        decayingProbabilityCheckBox.setMnemonic('D');
        decayingProbabilityCheckBox.setSelected(true);
        decayingProbabilityCheckBox.setToolTipText("");

        javax.swing.GroupLayout decayingProbabilityPanelLayout = new javax.swing.GroupLayout(decayingProbabilityPanel);
        decayingProbabilityPanel.setLayout(decayingProbabilityPanelLayout);
        decayingProbabilityPanelLayout.setHorizontalGroup(
            decayingProbabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decayingProbabilityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(decayingProbabilityLabel)
                .addGap(28, 28, 28)
                .addComponent(decayingProbabilityCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        decayingProbabilityPanelLayout.setVerticalGroup(
            decayingProbabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decayingProbabilityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(decayingProbabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(decayingProbabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decayingProbabilityCheckBox))
                .addContainerGap())
        );

        discountFactorResetButton.setText("↺");
        discountFactorResetButton.setToolTipText("Reset discount factor");
        discountFactorResetButton.setFocusable(false);
        discountFactorResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountFactorResetButtonActionPerformed(evt);
            }
        });

        discountFactorLabel.setDisplayedMnemonic('F');
        discountFactorLabel.setLabelFor(learningRateSpinner);
        discountFactorLabel.setText("Discount factor:");

        discountFactorSpinner.setModel(new javax.swing.SpinnerNumberModel(0.9d, 0.0d, 1.0d, 0.05d));

        javax.swing.GroupLayout discountFactorPanelLayout = new javax.swing.GroupLayout(discountFactorPanel);
        discountFactorPanel.setLayout(discountFactorPanelLayout);
        discountFactorPanelLayout.setHorizontalGroup(
            discountFactorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountFactorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(discountFactorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(discountFactorSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discountFactorResetButton)
                .addContainerGap())
        );
        discountFactorPanelLayout.setVerticalGroup(
            discountFactorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountFactorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(discountFactorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountFactorSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discountFactorResetButton)
                    .addComponent(discountFactorLabel))
                .addContainerGap())
        );

        learningRateResetButton.setText("↺");
        learningRateResetButton.setToolTipText("Reset learning rate");
        learningRateResetButton.setFocusable(false);
        learningRateResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                learningRateResetButtonActionPerformed(evt);
            }
        });

        learningRateSpinner.setModel(new javax.swing.SpinnerNumberModel(0.1d, 0.0d, 1.0d, 0.05d));

        learningRateLabel.setDisplayedMnemonic('L');
        learningRateLabel.setLabelFor(learningRateSpinner);
        learningRateLabel.setText("Learning rate:");

        javax.swing.GroupLayout learningRatePanelLayout = new javax.swing.GroupLayout(learningRatePanel);
        learningRatePanel.setLayout(learningRatePanelLayout);
        learningRatePanelLayout.setHorizontalGroup(
            learningRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(learningRatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(learningRateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(learningRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(learningRateResetButton)
                .addContainerGap())
        );
        learningRatePanelLayout.setVerticalGroup(
            learningRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(learningRatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(learningRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(learningRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(learningRateLabel)
                    .addComponent(learningRateResetButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout learningParametersPanelLayout = new javax.swing.GroupLayout(learningParametersPanel);
        learningParametersPanel.setLayout(learningParametersPanelLayout);
        learningParametersPanelLayout.setHorizontalGroup(
            learningParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(learningParametersPanelLayout.createSequentialGroup()
                .addGroup(learningParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(discountFactorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(learningRatePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rewardValuePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(explorationProbabilityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(decayingProbabilityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(helpButton)
                .addContainerGap())
        );
        learningParametersPanelLayout.setVerticalGroup(
            learningParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(learningParametersPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(rewardValuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(explorationProbabilityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(decayingProbabilityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(learningRatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(learningParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(helpButton)
                    .addComponent(discountFactorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        saveLoadPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Save/Load"));

        saveButton.setMnemonic('V');
        saveButton.setText("Save Setup");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setMnemonic('L');
        loadButton.setText("Load Setup");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout saveLoadPanelLayout = new javax.swing.GroupLayout(saveLoadPanel);
        saveLoadPanel.setLayout(saveLoadPanelLayout);
        saveLoadPanelLayout.setHorizontalGroup(
            saveLoadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveLoadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        saveLoadPanelLayout.setVerticalGroup(
            saveLoadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveLoadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(saveLoadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(loadButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        applyButton.setMnemonic('A');
        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout windowContentsLayout = new javax.swing.GroupLayout(windowContents);
        windowContents.setLayout(windowContentsLayout);
        windowContentsLayout.setHorizontalGroup(
            windowContentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(windowContentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(windowContentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveLoadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(learningParametersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(movementPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, windowContentsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton)))
                .addContainerGap())
        );
        windowContentsLayout.setVerticalGroup(
            windowContentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(windowContentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(learningParametersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(saveLoadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(windowContentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowContents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowContents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (haveSettingsChanged() && !Shared.promptFactory.setParent(this).setMessage("You have unsaved changes.<br>Are you sure you want to cancel?").setTitle("Unsaved Changes").setModal(true).create().getAnswer()) {
            return;
        }
        this.dispose();      
    }

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String[] helpMessages = {
            "The cardinal move type allows the agent to move in the four cardinal directions (up, down, left, right).",
            "The king move type allows the agent to move in all eight directions (up, down, left, right, and diagonals).",
            "The number of trials is the number of times the agent will traverse the gridworld.",
            "The number of moves is the number of maximum number of moves the agent can make in each trial.",
            "The out of bounds rule determines what happens when the agent moves out of bounds.",
            "The wind setting determines if there is wind in the simulation and what direction it blows.",
            "The speed setting determines how fast the simulation runs.\nIf you choose not to view the simulation, this setting has no effect.",
            "The reward value is the value that the agent will receive for each move it makes to a cell with no defined value. A positive value encourages exploration, a negative value discourages exploration.",
            "The exploration probability is the probability that the agent will explore a new move instead of taking the best move.",
            "When enabled, the probability that the agent will explore a new move instead of taking the best move decreases as time goes on.",
            "The learning rate is the rate at which the agent learns from each move. A higher value generally makes the agent learn faster, but may cause it to take a less than optimal path.",
            "The discount factor is the factor by which the agent discounts future rewards. A higher value means that the agent will learn LESS from repeated actions."
        };
        String[] helpTitles = {
            "Cardinal Move Type",
            "King Move Type",
            "Number of Trials",
            "Number of Moves",
            "Out of Bounds Rule",
            "Wind Setting",
            "Speed Setting",
            "Default Reward Value",
            "Exploration Probability",
            "Decaying Probability",
            "Learning Rate",
            "Discount Factor"
        };
        int selectedOption = Shared.optionFactory.setParent(this).setTitle("Select Help Topic").setMessage("Select the item that you need help with:").setOptions(helpTitles).setModal(true).create().getSelectedOption();
        if (selectedOption == -1) {
            return;
        }
        Shared.messageFactory.setParent(this).setMessage(helpMessages[selectedOption]).setTitle(helpTitles[selectedOption]).setModal(true).create();
        helpButton.doClick();
    }

    private void discountFactorResetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        discountFactorSpinner.setValue(0.9);
    }

    private void learningRateResetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        learningRateSpinner.setValue(0.1);
    }

    private void explorationProbabilityResetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        explorationProbabilitySpinner.setValue(0.1);
    }

    private void rewardValueResetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        rewardValueSpinner.setValue(0.0);
    }

    private void numOfMovesResetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        numOfMovesSpinner.setValue(100);      
    }

    private void numOfTrialsResetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        numOfTrialsSpinner.setValue(100);      
    }

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {
      
        Shared.stm.kingsMoves = kingRadioButton.isSelected();
        Shared.stm.numOfTrials = (int) numOfTrialsSpinner.getValue();
        Shared.stm.numOfMoves = (int) numOfMovesSpinner.getValue();
        Shared.stm.outOfBoundsRule = outOfBoundsRuleComboBox.getSelectedIndex();
        Shared.stm.useWind = windComboBox.getSelectedIndex() != 0;
        Shared.stm.windDirection = Math.max(0, windComboBox.getSelectedIndex() - 1);
        Shared.stm.simulationSpeed = new int[] {1000, 500, 200, 100, 1}[speedComboBox.getSelectedIndex()];
        Shared.stm.defaultReward = (double) rewardValueSpinner.getValue();
        Shared.stm.defaultExplorationProb = (double) explorationProbabilitySpinner.getValue();
        Shared.stm.explorationRateDecays = decayingProbabilityCheckBox.isSelected();
        Shared.stm.defaultLearningRate = (double) learningRateSpinner.getValue();
        Shared.stm.defaultDiscountFactor = (double) discountFactorSpinner.getValue();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applyButtonActionPerformed(null);
        this.dispose();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        cancelButtonActionPerformed(null);
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (haveSettingsChanged() && !Shared.promptFactory.setParent(this).setMessage("Apply changes before saving?").setTitle("Unsaved Changes").setModal(true).create().getAnswer()) {
            applyButtonActionPerformed(null);
        }
        Shared.saveFile();
    }

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if ((haveSettingsChanged() || Shared.stm.getNumOfReports() > 0) && !Shared.promptFactory.setParent(this).setMessage("Are you sure you want to load a new configuration?<br><span style='color: red'>Unsaved data will be lost!</span>").setTitle("Unsaved Changes").setModal(true).create().getAnswer()) {
            return;
        }
        Shared.loadFile("preferences", this::updateValues);
    }

    private void updateValues() {
        cardinalRadioButton.setSelected(!Shared.stm.kingsMoves);
        kingRadioButton.setSelected(Shared.stm.kingsMoves);
        numOfTrialsSpinner.setValue(Shared.stm.numOfTrials);
        numOfMovesSpinner.setValue(Shared.stm.numOfMoves);
        outOfBoundsRuleComboBox.setSelectedIndex(Shared.stm.outOfBoundsRule);
        int windIndex;
        if (!Shared.stm.useWind) {
            windIndex = 0;
        } else {
            windIndex = (Shared.stm.windDirection == 0) ? 1 : 2;
        }
        windComboBox.setSelectedIndex(windIndex);
        speedComboBox.setSelectedIndex(Shared.stm.simulationSpeed == 1000 ? 0 : Shared.stm.simulationSpeed == 500 ? 1 : Shared.stm.simulationSpeed == 200 ? 2 : Shared.stm.simulationSpeed == 100 ? 3 : 4);
        rewardValueSpinner.setValue(Shared.stm.defaultReward);
        explorationProbabilitySpinner.setValue(Shared.stm.defaultExplorationProb);
        decayingProbabilityCheckBox.setSelected(Shared.stm.explorationRateDecays);
        learningRateSpinner.setValue(Shared.stm.defaultLearningRate);
        discountFactorSpinner.setValue(Shared.stm.defaultDiscountFactor);
    }

    private void disableUnavailableOptions(boolean available) {
      
        cardinalRadioButton.setEnabled(available);
        kingRadioButton.setEnabled(available);
        numOfTrialsSpinner.setEnabled(available);
        numOfTrialsResetButton.setEnabled(available);
        numOfMovesSpinner.setEnabled(available);
        numOfMovesResetButton.setEnabled(available);
        outOfBoundsRuleComboBox.setEnabled(available);
        windComboBox.setEnabled(available);
        rewardValueSpinner.setEnabled(available);
        rewardValueResetButton.setEnabled(available);
        explorationProbabilitySpinner.setEnabled(available);
        explorationProbabilityResetButton.setEnabled(available);
        decayingProbabilityCheckBox.setEnabled(available);
        learningRateSpinner.setEnabled(available);
        learningRateResetButton.setEnabled(available);
        discountFactorSpinner.setEnabled(available);
        discountFactorResetButton.setEnabled(available);
        saveButton.setEnabled(available);
        loadButton.setEnabled(available);
    }

    private boolean haveSettingsChanged() {
      
        if (kingRadioButton.isSelected() != Shared.stm.kingsMoves) {
            return true;
        }
        if ((int) numOfTrialsSpinner.getValue() != Shared.stm.numOfTrials) {
            return true;
        }
        if ((int) numOfMovesSpinner.getValue() != Shared.stm.numOfMoves) {
            return true;
        }
        if (outOfBoundsRuleComboBox.getSelectedIndex() != Shared.stm.outOfBoundsRule) {
            return true;
        }
        if (windComboBox.getSelectedIndex() != (Shared.stm.useWind ? Shared.stm.windDirection + 1 : 0)) {
            return true;
        }
        if (new int[] {1000, 500 , 200, 100, 1}[speedComboBox.getSelectedIndex()] != Shared.stm.simulationSpeed) {
            return true;
        }
        if ((double) rewardValueSpinner.getValue() != Shared.stm.defaultReward) {
            return true;
        }
        if ((double) explorationProbabilitySpinner.getValue() != Shared.stm.defaultExplorationProb) {
            return true;
        }
        if (decayingProbabilityCheckBox.isSelected() != Shared.stm.explorationRateDecays) {
            return true;
        }
        if ((double) learningRateSpinner.getValue() != Shared.stm.defaultLearningRate) {
            return true;
        }
        return (double) discountFactorSpinner.getValue() != Shared.stm.defaultDiscountFactor;
    }

  
    private javax.swing.JButton applyButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel cardinalIcon;
    private javax.swing.JRadioButton cardinalRadioButton;
    private javax.swing.JCheckBox decayingProbabilityCheckBox;
    private javax.swing.JLabel decayingProbabilityLabel;
    private javax.swing.JPanel decayingProbabilityPanel;
    private javax.swing.JLabel discountFactorLabel;
    private javax.swing.JPanel discountFactorPanel;
    private javax.swing.JButton discountFactorResetButton;
    private javax.swing.JSpinner discountFactorSpinner;
    private javax.swing.JLabel explorationProbabilityLabel;
    private javax.swing.JPanel explorationProbabilityPanel;
    private javax.swing.JButton explorationProbabilityResetButton;
    private javax.swing.JSpinner explorationProbabilitySpinner;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel kingIcon;
    private javax.swing.JRadioButton kingRadioButton;
    private javax.swing.JPanel learningParametersPanel;
    private javax.swing.JLabel learningRateLabel;
    private javax.swing.JPanel learningRatePanel;
    private javax.swing.JButton learningRateResetButton;
    private javax.swing.JSpinner learningRateSpinner;
    private javax.swing.JButton loadButton;
    private javax.swing.ButtonGroup moveTypeButtonGroup;
    private javax.swing.JPanel movementPanel;
    private javax.swing.JLabel numOfMovesLabel;
    private javax.swing.JButton numOfMovesResetButton;
    private javax.swing.JSpinner numOfMovesSpinner;
    private javax.swing.JLabel numOfTrialsLabel;
    private javax.swing.JButton numOfTrialsResetButton;
    private javax.swing.JSpinner numOfTrialsSpinner;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox<String> outOfBoundsRuleComboBox;
    private javax.swing.JLabel outOfBoundsRuleLabel;
    private javax.swing.JLabel rewardValueLabel;
    private javax.swing.JPanel rewardValuePanel;
    private javax.swing.JButton rewardValueResetButton;
    private javax.swing.JSpinner rewardValueSpinner;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel saveLoadPanel;
    private javax.swing.JComboBox<String> speedComboBox;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JComboBox<String> windComboBox;
    private javax.swing.JLabel windLabel;
    private javax.swing.JPanel windowContents;
  

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (STM.SIMULATION_RUNNING_PROPERTY.equals(evt.getPropertyName())) {
            boolean isRunning = (Boolean) evt.getNewValue();
            SwingUtilities.invokeLater(() -> {
                disableUnavailableOptions(!isRunning);
            });
        }
        if (STM.CONFIGURATION_LOADED_PROPERTY.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(this::updateValues);
        }
    }

    @Override
    public void dispose() {
        Shared.stm.removePropertyChangeListener(this);
        super.dispose();
    }
}
