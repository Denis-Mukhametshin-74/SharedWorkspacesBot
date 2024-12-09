package com.example.GeneralMessages;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private List<String> messages;

    public MessageManager() {
        this.messages = new ArrayList<>();
    }

    public void createMessage(String text) {
        if (text != null && !text.trim().isEmpty()) {
            messages.add(text.trim());
        }
    }

    public boolean changeMessage(int index, String newText) {
        if (index >= 0 && index < messages.size()) {
            messages.set(index, newText.trim());
            return true;
        }
        return false;
    }

    public List<String> getMessages() {
        return messages;
    }

    public boolean deleteMessage(int index) {
        if (index >= 0 && index < messages.size()) {
            messages.remove(index);
            return true;
        }
        return false;
    }
}