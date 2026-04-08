package com.nemu.jgridworld;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class PromptFactory {

    private JFrame parent = Shared.mainWindow;
    private String title = "Please answer yes or no";
    private String message = "-- This window has no content --";
    private boolean dnaa = false;
    private String preferenceKey = "";
    private boolean isModal = false;
    private boolean isApplicationModal = false;
    private String defaultOption = "";
    private double timeout = 0.0;

    public PromptFactory setParent(JFrame parent) {
        this.parent = parent;
        return this;
    }

    public PromptFactory setTitle(String title) {
        this.title = title;
        return this;
    }

    public PromptFactory setMessage(String message) {
        this.message = message;
        return this;
    }

    public PromptFactory setDNAA(boolean dnaa) {
        this.dnaa = dnaa;
        return this;
    }

    public PromptFactory setPreferenceKey(String preferenceKey) {
        this.preferenceKey = preferenceKey;
        return this;
    }

    public PromptFactory setModal(boolean isModal) {
        this.isModal = isModal;
        return this;
    }

    public PromptFactory setApplicationModal(boolean isApplicationModal) {
        this.isApplicationModal = isApplicationModal;
        return this;
    }

    public PromptFactory setDefaultOption(String defaultOption) {
        this.defaultOption = defaultOption;
        return this;
    }

    public PromptFactory setTimeout(double timeout) {
        this.timeout = timeout;
        return this;
    }

    public GetYesOrNo create() {
        final AtomicReference<GetYesOrNo> promptRef = new AtomicReference<>();
        if (SwingUtilities.isEventDispatchThread()) {
          
            promptRef.set(doSetup());
        }
        else {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    promptRef.set(doSetup());
                });
            }
            catch (Exception e) {
                return null;
            }
        }
        reset();
        return promptRef.get();
    }

    public void reset() {
      
        this.parent = Shared.mainWindow;
        this.title = "Please answer yes or no";
        this.message = "-- This window has no content --";
        this.dnaa = false;
        this.preferenceKey = "";
        this.isModal = false;
        this.isApplicationModal = false;
        this.defaultOption = "";
        this.timeout = 0.0;
    }

    private GetYesOrNo doSetup() {
        String oldStatusBarText = Shared.getStatusBarText();
        String oldStatusBarState = Shared.getStatusBarState();
        Shared.setStatusBarText("Please answer yes or no");
        Shared.setStatusBarState("Waiting...!");
        GetYesOrNo prompt = new GetYesOrNo(parent, title, message, dnaa, preferenceKey, isModal, isApplicationModal, defaultOption, timeout);
        prompt.setVisible(true);
        Shared.setStatusBarText(oldStatusBarText);
        Shared.setStatusBarState(oldStatusBarState);
        reset();
        return prompt;
    }
}
