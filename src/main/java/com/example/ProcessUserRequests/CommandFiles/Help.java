package com.example.ProcessUserRequests.CommandFiles;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Help {

    public SendMessage sendHelpMessage(Long chatId) {

        String helpText =
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
        row1.add(InlineKeyboardButton.builder().text("Создать общее сообщение").callbackData("/create_message").build());
        row1.add(InlineKeyboardButton.builder().text("Изменить общее сообщение").callbackData("/change_message").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("Просмотреть все сообщения").callbackData("/view_messages").build());
        row2.add(InlineKeyboardButton.builder().text("Удалить общее сообщение").callbackData("/delete_message").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("Добавить задачу").callbackData("/create_todo").build());
        row3.add(InlineKeyboardButton.builder().text("Изменить статус задачи").callbackData("/status_todo").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("Просмотреть To-Do список").callbackData("/view_todos").build());
        row4.add(InlineKeyboardButton.builder().text("Удалить задачу").callbackData("/delete_todo").build());

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(InlineKeyboardButton.builder().text("Добавить напоминание").callbackData("/create_reminder").build());
        row5.add(InlineKeyboardButton.builder().text("Изменить напоминание").callbackData("/change_reminder").build());

        List<InlineKeyboardButton> row6 = new ArrayList<>();
        row6.add(InlineKeyboardButton.builder().text("Просмотреть напоминания").callbackData("/view_reminders").build());
        row6.add(InlineKeyboardButton.builder().text("Удалить напоминание").callbackData("/delete_reminder").build());

        buttons.add(row1);
        buttons.add(row2);
        buttons.add(row3);
        buttons.add(row4);
        buttons.add(row5);
        buttons.add(row6);

        keyboardMarkup.setKeyboard(buttons);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(helpText);
        message.setParseMode("HTML");
        message.setReplyMarkup(keyboardMarkup);

        return message;

    }

}