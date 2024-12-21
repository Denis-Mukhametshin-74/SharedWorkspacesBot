package com.example.ProcessUserRequests.CommandFiles.BasicCommands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Error {

    public SendMessage sendErrorMessage(Long chatId, Boolean waitingForMessage) {

        String errorText = " ";

        if (waitingForMessage == true) {
            errorText =
                "❌ <b>Ошибка:</b> Попробуйте ещё разок. 👌\r\n";
        }

        else if (waitingForMessage == false) {
            errorText =
                "⚠️ <b>Ошибка:</b> Неправильная команда\r\n" +
                "\r\n" +
                "• 💡 Вы ввели команду, которая не распознана ботом.\r\n" +
                "\r\n" +
                "• 👀 Пожалуйста, убедитесь, что ваша команда написана правильно и соответствует одному из доступных форматов.\r\n" +
                "\r\n" +
                "<b>Проверьте список доступных команд, введя /help, чтобы увидеть все доступные функции бота.</b> 🔍\r\n";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(errorText);
        message.setParseMode("HTML");

        return message;

    }

}