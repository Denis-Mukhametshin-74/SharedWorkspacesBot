package com.example.ProcessUserRequests.CommandFiles.CustomCommands;

import com.example.ProcessInternalEvents.ReminderManager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CreateReminder {

    private ReminderManager reminderManager;

    public CreateReminder() {
        this.reminderManager = new ReminderManager();
    }

    public SendMessage sendCreateReminder(Long chatId, Boolean waitingForMessage) {

        String createReminderText = " ";

        if (waitingForMessage == true) {
            createReminderText =
                "☑️ <b>Напоминание добавлено в список.</b> 👍\r\n";
        }

        else if (waitingForMessage == false) {
            createReminderText =
                "⏰ Введите напоминание, чтобы продолжить.\r\n" +
                "\r\n" +
                "<b>Если не хотите продолжать, просто введите /exit.</b> 🚫\r\n";

            reminderManager.saveUserReminder();
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(createReminderText);
        message.setParseMode("HTML");

        return message;

    }

}