package com.example.ProcessUserRequests.CommandFiles.CustomCommands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CreateMessage {

    public SendMessage sendCreateMessage(Long chatId, Boolean waitingForMessage) {

        String createMessageText = " ";

        if (waitingForMessage == true) {
            createMessageText =
                "<b>☑️ Сообщение добавлено в список.</b> 👍\r\n";
        }

        else if (waitingForMessage == false) {
            createMessageText =
                "📝 Введите текст общего сообщения, чтобы продолжить.\r\n" +
                "\r\n" +
                "<b>Если не хотите продолжать, просто введите /exit.</b> 🚫\r\n";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(createMessageText);
        message.setParseMode("HTML");

        return message;

    }

}