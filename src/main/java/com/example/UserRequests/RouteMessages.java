package com.example.UserRequests;
import com.example.UserRequests.CommandFiles.Help;
import com.example.UserRequests.CommandFiles.Start;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RouteMessages {

    private Start start;
    private Help help;

    public RouteMessages() {
        this.start = new Start();
        this.help = new Help();
    }

    public SendMessage handleUserInteraction(String commandData, Long chatId) {

        switch (commandData) {
            case "/start":
                return start.sendStartMessage(chatId);

            case "/help":
                return help.sendHelpMessage(chatId);

            default:
                break;
        }

        return null;

    }

}