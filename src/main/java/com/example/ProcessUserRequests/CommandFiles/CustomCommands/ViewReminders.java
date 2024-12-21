package com.example.ProcessUserRequests.CommandFiles.CustomCommands;

import com.example.ProcessInternalEvents.ReminderManager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ViewReminders {

    private ReminderManager reminderManager;

    public ViewReminders() {
        this.reminderManager = new ReminderManager();
    }

    public SendMessage sendViewReminders(Long chatId) {

        String viewRemindersText = " ";

        if (reminderManager.remindersListIsEmpty == true) {
            viewRemindersText =
                "🤔 Нет добавленных напоминаний.\r\n" +
                "\r\n" +
                "<b>Введите /create_reminder для добавления напоминания.</b> ⏲️\r\n";
        }

        else if (reminderManager.remindersListIsEmpty == false) {
            viewRemindersText =
                "⚡️ <b>Вот ваш список напоминаний:</b> 👇\r\n";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(viewRemindersText);
        message.setParseMode("HTML");

        return message;

    }

}