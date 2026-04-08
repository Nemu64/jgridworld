
package com.nemu.jgridworld;

import javax.swing.*;
import java.awt.*;


public class Message extends javax.swing.JDialog {

    public Message(JFrame parent, String title, String message, boolean dnsa, String preferenceKey, boolean isModal) {
        super(parent, title, (isModal) ? ModalityType.DOCUMENT_MODAL : Dialog.ModalityType.MODELESS);
        initComponents();
        this.setTitle(title);
        this.message.setText("<html>" + message + "</html>");
        this.dnsaCheckbox.setVisible(dnsa);
        this.dnsaCheckbox.setEnabled(dnsa);
        this.okButton.requestFocus();
        this.pack();
        this.preferenceKey = preferenceKey;
        this.setLocationRelativeTo(parent);
    }

    
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        exclamationMarkImageLabel = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        dnsaCheckbox = new javax.swing.JCheckBox();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Information");
        setResizable(false);

        exclamationMarkImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exclamationMarkImageLabel.setIcon(Images.EXCLAMATION_MARK);
        exclamationMarkImageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        exclamationMarkImageLabel.setIconTextGap(0);

        message.setFont(new java.awt.Font("Tahoma", 0, 12));
        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        message.setText("-- This window has no content --");
        message.setToolTipText("");
        message.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        message.setIconTextGap(32);
        message.setMaximumSize(new java.awt.Dimension(170, 15));

        dnsaCheckbox.setMnemonic('D');
        dnsaCheckbox.setText("Do not show me this again");

        okButton.setMnemonic('O');
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exclamationMarkImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dnsaCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(exclamationMarkImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dnsaCheckbox)
                    .addComponent(okButton))
                .addContainerGap())
        );

        pack();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (dnsaCheckbox.isSelected()) {
            if (preferenceKey.isEmpty()) {
                Shared.log("DNSA preference key is empty. No preference set.", "warning");
            }
            else {
                Shared.preferenceManager.setPromptFlag(preferenceKey);
            }
        }
        this.dispose();
    }
  
    private javax.swing.JCheckBox dnsaCheckbox;
    private javax.swing.JLabel exclamationMarkImageLabel;
    private javax.swing.JLabel message;
    private javax.swing.JButton okButton;
  
    private String preferenceKey = "";
}