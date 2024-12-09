package com.example.GeneralMessages;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private List<String> messages;

    public MessageManager() {
        this.messages = new ArrayList<>();
    }

    public void createMessage(String text) {
        messages.add(text);
    }

    public boolean changeMessage(int index, String newText) {
        if (index >= 0 && index < messages.size()) {
            messages.set(index, newText);
            return true;
        }
        return false;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getMessage(int index) {
        if (index >= 0 && index < messages.size()) {
            return messages.get(index);
        }
        return null;
    }
}