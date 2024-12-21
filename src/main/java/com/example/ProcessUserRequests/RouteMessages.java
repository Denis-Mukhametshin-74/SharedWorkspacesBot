package com.example.ProcessUserRequests;

import com.example.ProcessUserRequests.CommandFiles.Error;
import com.example.ProcessUserRequests.CommandFiles.Help;
import com.example.ProcessUserRequests.CommandFiles.Start;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RouteMessages {

    private Start start;
    private Help help;
    private Error error;

    public RouteMessages() {
        this.start = new Start();
        this.help = new Help();
        this.error = new Error();
    }

    public SendMessage handleUserInteraction(String commandData, Long chatId) {

        switch (commandData) {
            case "/start":
                return start.sendStartMessage(chatId);

            case "/help":
                return help.sendHelpMessage(chatId);

            default:
                return error.sendErrorMessage(chatId);
        }

    }

}