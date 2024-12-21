package com.example.ProcessUserRequests.CommandFiles.BasicCommands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Exit {

    public SendMessage sendExitMessage(Long chatId) {

        String exitText = 
            "👋 Вы вышли из текущего режима.\r\n" +
            "\r\n" +
            "<b>Если хотите что-то сделать, просто введите команду!</b> 😊\r\n";

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(exitText);
        message.setParseMode("HTML");

        return message;

    }

}