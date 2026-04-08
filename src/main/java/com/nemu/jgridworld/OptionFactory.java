package com.nemu.jgridworld;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class OptionFactory {
    private String title = "Select Option";
    private String message = "Select an option:";
    private String[] options = new String[]{"Option 1", "Option 2", "Option 3"};
    private JFrame parent = null;
    private boolean isModal = false;

    public OptionFactory setTitle(String title) {
        this.title = title;
        return this;
    }

    public OptionFactory setMessage(String message) {
        this.message = message;
        return this;
    }

    public OptionFactory setOptions(String[] options) {
        this.options = options;
        return this;
    }

    public OptionFactory setModal(boolean isModal) {
        this.isModal = isModal;
        return this;
    }

    public OptionFactory setParent(JFrame parent) {
        this.parent = parent;
        return this;
    }

    public OptionWindow create() {
        final AtomicReference<OptionWindow> optionWindowRef = new AtomicReference<>();
        if (SwingUtilities.isEventDispatchThread()) {
            OptionWindow optionWindow = new OptionWindow(parent, title, message, options, isModal);
            optionWindowRef.set(optionWindow);
            optionWindow.setVisible(true);
        }
        else {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    OptionWindow optionWindow = new OptionWindow(parent, title, message, options, isModal);
                    optionWindowRef.set(optionWindow);
                    optionWindow.setVisible(true);
                });
            }
            catch (Exception e) {
                return null;
            }
        }
        reset();
        return optionWindowRef.get();
    }

    public void reset() {
        this.title = "Select Option";
        this.message = "Select an option:";
        this.options = new String[]{"Option 1", "Option 2", "Option 3"};
        this.parent = null;
        this.isModal = false;
    }
}
