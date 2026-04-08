package com.nemu.jgridworld;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

public class MessageFactory {

    private JFrame parent = Shared.mainWindow;
    private String title = "Information";
    private String message = "-- This window has no content --";
    private boolean dnsa = false;
    private String preferenceKey = "";
    private boolean isModal = false;

    public MessageFactory setParent(JFrame parent) {
        this.parent = parent;
        return this;
    }

    public MessageFactory setTitle(String title) {
        this.title = title;
        return this;
    }

    public MessageFactory setMessage(String message) {
        this.message = message;
        return this;
    }

    public MessageFactory setDNSA(boolean dnsa) {
        this.dnsa = dnsa;
        return this;
    }

    public MessageFactory setPreferenceKey(String preferenceKey) {
        this.preferenceKey = preferenceKey;
        return this;
    }

    public MessageFactory setModal(boolean isModal) {
        this.isModal = isModal;
        return this;
    }

    public Message create() {
      
        final AtomicReference<Message> msgRef = new AtomicReference<>();

      
        if (SwingUtilities.isEventDispatchThread()) {
          
            Message msg = new Message(parent, title, message, dnsa, preferenceKey, isModal);
            msgRef.set(msg);
            msg.setVisible(true);
        }
        else {
          
            try {
                SwingUtilities.invokeAndWait(() -> {
                    Message msg = new Message(parent, title, message, dnsa, preferenceKey, isModal);
                    msgRef.set(msg);
                    msg.setVisible(true);
                });
            }
            catch (InterruptedException | InvocationTargetException e) {
                return null;
            }
        }
        reset();
        return msgRef.get();
    }

    public void reset() {
        this.parent = Shared.mainWindow;
        this.title = "Information";
        this.message = "-- This window has no content --";
        this.dnsa = false;
        this.preferenceKey = "";
        this.isModal = false;
    }
}
