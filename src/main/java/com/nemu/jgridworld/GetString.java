
package com.nemu.jgridworld;

import javax.swing.*;
import javax.swing.text.PlainDocument;


public class GetString extends javax.swing.JDialog {

    
    public GetString(JFrame parent, String title, String prompt, String regexPattern) {
        super(parent, title, ModalityType.DOCUMENT_MODAL);
        this.regexPattern = regexPattern;
        initComponents();
        this.setTitle(title);
        this.prompt.setText("<html>" + prompt + "</html>");
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        prompt = new javax.swing.JLabel();
        textField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        cancelButton.setMnemonic('C');
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

        prompt.setFont(new java.awt.Font("Tahoma", 0, 12));
        prompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prompt.setText("-- This window has no content --");

        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prompt, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(prompt, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        ((PlainDocument) textField.getDocument()).setDocumentFilter(Shared.createDocumentFilter(regexPattern));

        pack();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (textField.getText().isEmpty()) {
            Shared.messageFactory.setTitle("Error").
                                 setParent((JFrame) this.getOwner())
                                 .setMessage("Please enter a value.")
                                 .setModal(true)
                                 .create();
        }
        else {
            this.dispose();
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        textField.setText("");
        this.dispose();
    }

    private void textFieldKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okButtonActionPerformed(null);
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            cancelButtonActionPerformed(null);
        }
    }

    public String getString() {
        return textField.getText();
    }

  
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel prompt;
    private javax.swing.JTextField textField;
  
    private String regexPattern = ".*";
}
