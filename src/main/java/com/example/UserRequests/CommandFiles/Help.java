package com.example.UserRequests.CommandFiles;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Help {

    public SendMessage sendHelpMessage(Long chatId) {

        String welcomeText =
            "✨ Вот список доступных команд: ✨\r\n" +
            "\r\n" +
            "📩 <b>Создание сообщений:</b>\r\n" +
            "\r\n" +
            "• 📝 Создать общее сообщение для вашей команды.\r\n" +
            "\r\n" +
            "• ✏️ Изменить одно из общих сообщений.\r\n" +
            "\r\n" +
            "• ❌ Удалить одно из общих сообщений.\r\n" +
            "\r\n" +
            "• 👀 Просмотреть все сообщения.\r\n" +
            "\r\n" +
            "✅ <b>Управление задачами:</b>\r\n" +
            "\r\n" +
            "• ➕ Добавить задачу в To-Do список.\r\n" +
            "\r\n" +
            "• 🔄 Изменить текущий статус выполнения задачи.\r\n" +
            "\r\n" +
            "• 📋 Просмотреть текущий To-Do список.\r\n" +
            "\r\n" +
            "• 🗑 Удалить задачу из To-Do списка.\r\n" +
            "\r\n" +
            "⏰ <b>Управление напоминаниями:</b>\r\n" +
            "\r\n" +
            "• ⏲️ Добавить напоминание.\r\n" +
            "\r\n" +
            "• 🔔 Изменить существующее напоминание.\r\n" +
            "\r\n" +
            "• 🚫 Удалить напоминание.\r\n" +
            "\r\n" +
            "• 📅 Просмотреть все напоминания.\r\n" +
            "\r\n" +
            "❓ <b>Если у вас есть вопросы или нужна помощь, напишите @LaR4uK!</b> 🤝\r\n";

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("Общее сообщение").callbackData("/create_message").build());
        row1.add(InlineKeyboardButton.builder().text("To-Do задача").callbackData("/create_todo").build());
    
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("Напоминание").callbackData("/create_reminder").build());
        row2.add(InlineKeyboardButton.builder().text("Помощь").callbackData("/help").build());

        buttons.add(row1);
        buttons.add(row2);

        keyboardMarkup.setKeyboard(buttons);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(welcomeText);
        message.setParseMode("HTML");
        message.setReplyMarkup(keyboardMarkup);

        return message;

    }

}