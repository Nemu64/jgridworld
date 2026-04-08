
package com.nemu.jgridworld;

import javax.swing.*;


public class OptionWindow extends javax.swing.JDialog {

    
    public OptionWindow(JFrame parent, String title, String message, String[] options, boolean isModal) {
        super(parent, title, (isModal) ? ModalityType.DOCUMENT_MODAL : ModalityType.MODELESS);
        initComponents();
        ((JLabel)this.selectOptionComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        this.setTitle(title);
        selectOptionLabel.setText("<html>" + message + "</html>");
        selectOptionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(options));
        this.setLocationRelativeTo(parent);
    }

    
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        windowContents = new javax.swing.JPanel();
        selectOptionLabel = new javax.swing.JLabel();
        selectOptionComboBox = new javax.swing.JComboBox<>();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select Option");
        setResizable(false);

        selectOptionLabel.setDisplayedMnemonic('S');
        selectOptionLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
        selectOptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectOptionLabel.setText("Select an option:");

        selectOptionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                .addComponent(selectOptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(windowContentsLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, windowContentsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(selectOptionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        windowContentsLayout.setVerticalGroup(
            windowContentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(windowContentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectOptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectOptionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(okButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowContents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowContents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        selectedOption = selectOptionComboBox.getSelectedIndex();
        this.dispose();
    }

  
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox<String> selectOptionComboBox;
    private javax.swing.JLabel selectOptionLabel;
    private javax.swing.JPanel windowContents;
  
    private int selectedOption = -1;
    public int getSelectedOption() {
        return selectedOption;
    }
}
