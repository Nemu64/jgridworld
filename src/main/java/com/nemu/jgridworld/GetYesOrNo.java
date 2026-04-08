
package com.nemu.jgridworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GetYesOrNo extends javax.swing.JDialog {

    
    public GetYesOrNo(JFrame parent, String title, String message, boolean dnaa, String preferenceKey, boolean isModal, boolean isApplicationModal, String defaultOption, double timeout) {
        super(parent, title, (isApplicationModal) ? ModalityType.APPLICATION_MODAL : (isModal) ? ModalityType.DOCUMENT_MODAL : Dialog.ModalityType.MODELESS);
        initComponents();
        this.setTitle(title);
        this.prompt.setText("<html>" + message + "</html>");
        this.dnaaCheckbox.setVisible(dnaa);
        this.dnaaCheckbox.setEnabled(dnaa);
        if (dnaa && preferenceKey.startsWith("remember")) {
            this.dnaaCheckbox.setText("Remember my choice");
            this.dnaaCheckbox.setMnemonic('R');
        }
        this.preferenceKey = preferenceKey;
        this.pack();
        this.setLocationRelativeTo(parent);
        if (!defaultOption.isEmpty()) {
            if (defaultOption.equalsIgnoreCase("yes")) {
                yesButton.requestFocus();
            }
            else if (defaultOption.equalsIgnoreCase("no")) {
                noButton.requestFocus();
            }
        }
        if (!defaultOption.isEmpty() && timeout > 0) {

          
            this.updateButtonText = new Timer(1000, new ActionListener() {
                int seconds = (int) timeout;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (defaultOption.equalsIgnoreCase("yes")) {
                        yesButton.setText("Yes (" + seconds + ")");
                    }
                    else if (defaultOption.equalsIgnoreCase("no")) {
                        noButton.setText("No (" + seconds + ")");
                    }
                    seconds--;
                }
            });
            updateButtonText.setInitialDelay(0);
            updateButtonText.start();
            closeDialog = new Timer((int) (timeout * 1000), e -> {
                setAnswer(defaultOption.equalsIgnoreCase("yes"));
                setDnaaPreference();
            });
            closeDialog.setRepeats(false);
            closeDialog.start();
        }
    }

    
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        prompt = new javax.swing.JLabel();
        noButton = new javax.swing.JButton();
        yesButton = new javax.swing.JButton();
        dnaaCheckbox = new javax.swing.JCheckBox();
        questionMarkImageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Please answer yes or no");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        prompt.setFont(new java.awt.Font("Tahoma", 0, 12));
        prompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prompt.setText("-- This window has no content --");
        prompt.setToolTipText("");
        prompt.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        prompt.setIconTextGap(32);
        prompt.setMaximumSize(new java.awt.Dimension(170, 15));

        noButton.setMnemonic('N');
        noButton.setText("No");
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });

        yesButton.setMnemonic('Y');
        yesButton.setText("Yes");
        yesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonActionPerformed(evt);
            }
        });

        dnaaCheckbox.setMnemonic('D');
        dnaaCheckbox.setText("Do not ask me this again");

        questionMarkImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionMarkImageLabel.setIcon(Images.QUESTION_MARK);
        questionMarkImageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        questionMarkImageLabel.setIconTextGap(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dnaaCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(yesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(questionMarkImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(prompt, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prompt, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(questionMarkImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noButton)
                    .addComponent(yesButton)
                    .addComponent(dnaaCheckbox))
                .addContainerGap())
        );

        pack();
    }

    private void yesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        destroyTimers();
        this.setAnswer(true);
        setDnaaPreference();
    }

    private void setDnaaPreference() {
        if (dnaaCheckbox.isEnabled() && dnaaCheckbox.isSelected()) {
            if (preferenceKey.isEmpty()) {
                Shared.log("DNAA preference key is empty. No preference set.", "warning");
            }
            else {
                if (preferenceKey.startsWith("remember")) {
                    Shared.preferenceManager.setPromptFlag(preferenceKey, answer);
                }
                else Shared.preferenceManager.setPromptFlag(preferenceKey);
            }
        }
        this.dispose();
    }

    private void destroyTimers() {
        if (updateButtonText != null) {
            updateButtonText.stop();
        }
        if (closeDialog != null) {
            closeDialog.stop();
        }
    }

    private void noButtonActionPerformed(java.awt.event.ActionEvent evt) {
        destroyTimers();
        this.setAnswer(false);
        setDnaaPreference();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        this.setAnswer(false);
        this.dispose();
    }

  
    private javax.swing.JCheckBox dnaaCheckbox;
    private javax.swing.JButton noButton;
    private javax.swing.JLabel prompt;
    private javax.swing.JLabel questionMarkImageLabel;
    private javax.swing.JButton yesButton;
  
    private String preferenceKey = "";
    private boolean answer = false;
    private Timer updateButtonText;
    private Timer closeDialog;

    public boolean getAnswer() {
        return answer;
    }

    private void setAnswer(boolean answer) {
        this.answer = answer;
    }
}