package com.example.UserRequests.CommandFiles;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

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

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(helpText);
        message.setParseMode("HTML");

        return message;

    }

}