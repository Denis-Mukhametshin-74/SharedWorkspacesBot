package com.example.ProcessUserRequests.CommandFiles.CustomCommands;

import com.example.ProcessInternalEvents.MessageManager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ViewMessages {

    private MessageManager messageManager;

    public ViewMessages() {
        this.messageManager = new MessageManager();
    }

    public SendMessage sendViewMessages(Long chatId) {

        String viewMessagesText = " ";

        if (messageManager.messagesListIsEmpty == true) {
            viewMessagesText =
                "🤔 Нет созданных общих сообщений.\r\n" +
                "\r\n" +
                "<b>Введите /create_message для создания общего сообщения.</b> 📝\r\n";
        }

        else if (messageManager.messagesListIsEmpty == false) {
            viewMessagesText =
                "⚡️ <b>Вот ваш список общих сообщений:</b> 👇\r\n";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(viewMessagesText);
        message.setParseMode("HTML");

        return message;

    }

}