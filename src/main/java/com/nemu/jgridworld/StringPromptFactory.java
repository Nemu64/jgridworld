package com.nemu.jgridworld;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class StringPromptFactory {
    private JFrame parent = null;
    private String title = "Please enter a string";
    private String message = "-- This window has no content --";
    private String regexPattern = ".*";

    public StringPromptFactory setParent(JFrame parent) {
        this.parent = parent;
        return this;
    }

    public StringPromptFactory setTitle(String title) {
        this.title = title;
        return this;
    }

    public StringPromptFactory setMessage(String message) {
        this.message = message;
        return this;
    }

    public StringPromptFactory setRegexPattern(String regexPattern) {
        this.regexPattern = regexPattern;
        return this;
    }

    public GetString create() {
        final AtomicReference<GetString> promptRef = new AtomicReference<>();
        if (SwingUtilities.isEventDispatchThread()) {
          
            GetString prompt = new GetString(parent, title, message, regexPattern);
            promptRef.set(prompt);
        }
        else {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    GetString prompt = new GetString(parent, title, message, regexPattern);
                    promptRef.set(prompt);
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
        this.parent = null;
        this.title = "Please enter a string";
        this.message = "-- This window has no content --";
        this.regexPattern = ".*";
    }
}
