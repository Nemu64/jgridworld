package com.nemu.jgridworld;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class IOS {

    public File openFileDialog(JFrame parent, String title, String fileExtension, String description, boolean onlyDirectories) {
        SimFileChooser chooser = new SimFileChooser(parent, title, fileExtension, description, onlyDirectories, JFileChooser.OPEN_DIALOG);
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            Shared.lastFile = chooser.getSelectedFile();
            return Shared.lastFile;
        }
        return null;
    }

   public File saveFileDialog(JFrame parent, String title, String fileExtension, String description, boolean onlyDirectories) {
        SimFileChooser chooser = new SimFileChooser(parent, title, fileExtension, description, onlyDirectories, JFileChooser.SAVE_DIALOG);
        if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (!selectedFile.getName().endsWith(fileExtension)) {
                selectedFile = new File(selectedFile.getAbsolutePath() + fileExtension);
            }
            Shared.lastFile = selectedFile;
            return Shared.lastFile;
        }
        return null;
    }

    public String setDirectoryDialog(JFrame parent, String title, String description) {
        SimFileChooser chooser = new SimFileChooser(parent, title, "", description, true, JFileChooser.OPEN_DIALOG);
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    private static class SimFileChooser extends JFileChooser {
        private final String fileExtension;
        private final boolean onlyDirectories;
        private final int dialogType;

        public SimFileChooser(JFrame parent, String title, String fileExtension, String description, boolean onlyDirectories, int dialogType) {
            this.fileExtension = fileExtension;
            this.onlyDirectories = onlyDirectories;
            this.dialogType = dialogType;
            this.setFileSelectionMode((onlyDirectories) ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_AND_DIRECTORIES);
            if (onlyDirectories) {
                this.setApproveButtonText("Select Folder");
                this.setApproveButtonMnemonic('S');
                this.setApproveButtonToolTipText("Use this directory");
            }
            setPreferredSize(new Dimension(600, 450));
            setDialogType(dialogType);
            setDialogTitle(title);
            setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(fileExtension) && dialogType == JFileChooser.OPEN_DIALOG;
                }

                @Override
                public String getDescription() {
                    return description;
                }
            });
            setAcceptAllFileFilterUsed(false);
            setCurrentDirectory(Shared.lastFile != null ? new File(Shared.lastFile.getAbsolutePath()) : new File(Shared.preferenceManager.getPreference("fileDefaultConfigDirectory")));
            Shared.log("Waiting for user to select a " + (onlyDirectories ? "directory" : "file") + "...");
            Shared.setStatusBarText("Waiting for " + (onlyDirectories ? "directory" : "file") + "...");
            Shared.setStatusBarState("Waiting...!");
        }

        @Override
        public void approveSelection() {
            if (getSelectedFile().getName().endsWith(fileExtension) && dialogType == OPEN_DIALOG || getSelectedFile().isDirectory() && onlyDirectories || dialogType == SAVE_DIALOG) {
                super.approveSelection();
            }
        }
    }
}
