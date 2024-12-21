package com.example.ProcessUserRequests;

import com.example.ProcessUserRequests.CommandFiles.BasicCommands.Error;
import com.example.ProcessUserRequests.CommandFiles.BasicCommands.Exit;
import com.example.ProcessUserRequests.CommandFiles.BasicCommands.Help;
import com.example.ProcessUserRequests.CommandFiles.BasicCommands.Start;

import com.example.ProcessUserRequests.CommandFiles.CustomCommands.CreateMessage;
import com.example.ProcessUserRequests.CommandFiles.CustomCommands.CreateTodo;
import com.example.ProcessUserRequests.CommandFiles.CustomCommands.CreateReminder;

import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RouteMessages {

    private Start start;
    private Help help;
    private Error error;
    private Exit exit;

    private CreateMessage createMessage;
    private CreateTodo createTodo;
    private CreateReminder createReminder;

    private Map<Long, WaitingState> waitingForInput = new HashMap<>();
    private enum WaitingState {
        NONE,
        CREATING_MESSAGE,
        CREATING_TASK,
        CREATING_REMINDER
    }

    public RouteMessages() {
        this.start = new Start();
        this.help = new Help();
        this.error = new Error();
        this.exit = new Exit();

        this.createMessage = new CreateMessage();
        this.createTodo = new CreateTodo();
        this.createReminder = new CreateReminder();
    }

    public SendMessage handleUserInteraction(String commandData, Long chatId) {

        WaitingState state = waitingForInput.getOrDefault(chatId, WaitingState.NONE);

        if (state != WaitingState.NONE) {
            switch (commandData) {
                case "/exit":
                    waitingForInput.put(chatId, WaitingState.NONE);
                    return exit.sendExitMessage(chatId);

                default:
                    switch (state) {
                        case CREATING_MESSAGE:
                            waitingForInput.put(chatId, WaitingState.NONE);
                            return createMessage.sendCreateMessage(chatId, true);

                        case CREATING_TASK:
                            waitingForInput.put(chatId, WaitingState.NONE);
                            return createTodo.sendCreateTodo(chatId, true);

                        case CREATING_REMINDER:
                            waitingForInput.put(chatId, WaitingState.NONE);
                            return createReminder.sendCreateReminder(chatId, true);

                        default:
                            return error.sendErrorMessage(chatId, true);
                    }

            }

        }

        switch (commandData) {
            case "/start":
                return start.sendStartMessage(chatId);

            case "/help":
                return help.sendHelpMessage(chatId);

            case "/create_message":
                waitingForInput.put(chatId, WaitingState.CREATING_MESSAGE);
                return createMessage.sendCreateMessage(chatId, false);

            case "/create_todo":
                waitingForInput.put(chatId, WaitingState.CREATING_TASK);
                return createTodo.sendCreateTodo(chatId, false);

            case "/create_reminder":
                waitingForInput.put(chatId, WaitingState.CREATING_REMINDER);
                return createReminder.sendCreateReminder(chatId, false);

            default:
                return error.sendErrorMessage(chatId, false);
        }

    }

}