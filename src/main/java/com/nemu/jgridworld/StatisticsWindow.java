
package com.nemu.jgridworld;

import java.beans.PropertyChangeEvent;
import java.time.Duration;


public class StatisticsWindow extends javax.swing.JFrame implements java.beans.PropertyChangeListener {

    
    public StatisticsWindow() {
        initComponents();
        this.populateTable();
        Shared.stm.addPropertyChangeListener(this);
        this.refreshButton.requestFocus();
        this.setLocationRelativeTo(Shared.mainWindow);
        this.setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        statisticsScrollPane = new javax.swing.JScrollPane();
        statisticsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Statistics");
        setIconImage(Images.TITLE_BAR_ICON);
        setResizable(false);

        closeButton.setMnemonic('C');
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        refreshButton.setMnemonic('R');
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.setToolTipText("Reset all statistics");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        statisticsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Statistic", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        statisticsTable.setEnabled(false);
        statisticsTable.setFocusable(false);
        statisticsTable.setRowSelectionAllowed(false);
        statisticsTable.setShowGrid(true);
        statisticsTable.getTableHeader().setReorderingAllowed(false);
        statisticsScrollPane.setViewportView(statisticsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statisticsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statisticsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(refreshButton)
                    .addComponent(resetButton))
                .addContainerGap())
        );

        pack();
    }

    private void populateTable() {
      
        statisticsTable.setValueAt("Total times run", 0, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsInt("statsTotalTimesRun"), 0, 1);
      
        statisticsTable.setValueAt("Total uptime (excluding this session)", 1, 0);
        if (Shared.preferenceManager.getStatisticAsLong("statsTotalUptime") == 0) statisticsTable.setValueAt("0:00:00:00", 1, 1);
        else {
            Duration duration = Duration.ofMillis(Shared.preferenceManager.getStatisticAsLong("statsTotalUptime"));
            long days = duration.toDaysPart();
            long hours = duration.toHoursPart();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();
            String formattedDuration = String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
            statisticsTable.setValueAt(formattedDuration, 1, 1);
        }
      
        statisticsTable.setValueAt("Total simulations started", 2, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsInt("statsTotalNumSimulations"), 2, 1);
      
        statisticsTable.setValueAt("Total simulations completed", 3, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsLong("statsTotalNumCompletedSimulations"), 3, 1);
      
        statisticsTable.setValueAt("Total simulations aborted", 4, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsLong("statsTotalNumAbortedSimulations"), 4, 1);
      
        statisticsTable.setValueAt("Total trials across all simulations", 5, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsLong("statsTotalNumTrials"), 5, 1);
      
        statisticsTable.setValueAt("Total successful trials", 6, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsLong("statsTotalNumSuccesses"), 6, 1);
      
        statisticsTable.setValueAt("Total moves across all simulations", 7, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsLong("statsTotalNumMoves"), 7, 1);
      
        statisticsTable.setValueAt("Total exploratory moves", 8, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsLong("statsTotalNumExploratoryMoves"), 8, 1);
      
        statisticsTable.setValueAt("Total exploitative moves", 9, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsLong("statsTotalNumExploitativeMoves"), 9, 1);
      
        statisticsTable.setValueAt("Total agent deaths", 10, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsLong("statsTotalNumAgentDeaths"), 10, 1);
      
        statisticsTable.setValueAt("Highest number of reports at once", 11, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsInt("statsTotalNumReportsAtOnce"), 11, 1);
      
        statisticsTable.setValueAt("Total number of reports voided", 12, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsInt("statsTotalNumReportsVoided"), 12, 1);
      
        statisticsTable.setValueAt("Total number of reports printed", 13, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsInt("statsTotalNumReportsPrinted"), 13, 1);
      
        statisticsTable.setValueAt("Total GridWorlds created", 14, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsInt("statsTotalNumGridWorldsCreated"), 14, 1);
      
        statisticsTable.setValueAt("Total configurations saved", 15, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsInt("statsTotalNumConfigsSaved"), 15, 1);
      
        statisticsTable.setValueAt("Total configurations loaded", 16, 0);
        statisticsTable.setValueAt(Shared.preferenceManager.getStatisticAsInt("statsTotalNumConfigsLoaded"), 16, 1);
    }

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.populateTable();
    }

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        boolean resetConfirmed = Shared.promptFactory.setParent(this).setTitle("Reset Statistics").setMessage("Are you sure you want to reset all statistics?<br><span style='color: red'>This cannot be undone!</span>").setModal(true).setDefaultOption("no").create().getAnswer();
        if (resetConfirmed) {
            Shared.preferenceManager.resetStatistics();
            Shared.log("Statistics reset", "info");
            this.populateTable();
        }
      
    }

  
    private javax.swing.JButton closeButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JScrollPane statisticsScrollPane;
    private javax.swing.JTable statisticsTable;
  

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (STM.SIMULATION_RUNNING_PROPERTY.equals(evt.getPropertyName())) {
            boolean isRunning = (Boolean) evt.getNewValue();
            if (!isRunning) {
                populateTable();
            }
        }
    }

    @Override
    public void dispose() {
        Shared.stm.removePropertyChangeListener(this);
        super.dispose();
    }
}
