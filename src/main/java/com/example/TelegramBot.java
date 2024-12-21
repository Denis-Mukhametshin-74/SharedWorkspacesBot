package com.example;
import com.example.UserRequests.RouteMessages;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@SuppressWarnings("deprecation")
public class TelegramBot extends TelegramLongPollingBot {

    private RouteMessages routeMessages;

    public TelegramBot() {
        this.routeMessages = new RouteMessages();
    }

    @Override
    public String getBotUsername() {
        return System.getenv("bot-name");
    }

    @Override
    public String getBotToken() {
        return System.getenv("bot-token");
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String callbackData = callbackQuery.getData();
            Long chatIdForButtons = callbackQuery.getMessage().getChatId();

            sendMessage(routeMessages.handleUserInteraction(callbackData, chatIdForButtons));
        }

        else if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String[] commandParts = messageText.split(" ", 2);
            Long chatIdForChat = update.getMessage().getChatId();

            sendMessage(routeMessages.handleUserInteraction(commandParts[0], chatIdForChat));
        }

    }

    public void sendMessage(SendMessage message) {

        try {
            execute(message);
        }

        catch (TelegramApiException error) {
            error.printStackTrace();
        }

    }

}