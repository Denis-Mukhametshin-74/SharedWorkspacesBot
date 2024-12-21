package com.example.ProcessUserRequests.CommandFiles.CustomCommands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CreateTodo {

    public SendMessage sendCreateTodo(Long chatId, Boolean waitingForMessage) {

        String createTodoText = " ";

        if (waitingForMessage == true) {
            createTodoText =
                "<b>☑️ Задача добавлена в список.</b> 👍\r\n";
        }

        else if (waitingForMessage == false) {
            createTodoText =
                "✅ Введите задачу, чтобы продолжить.\r\n" +
                "\r\n" +
                "<b>Если не хотите продолжать, просто введите /exit.</b> 🚫\r\n";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(createTodoText);
        message.setParseMode("HTML");

        return message;

    }

}