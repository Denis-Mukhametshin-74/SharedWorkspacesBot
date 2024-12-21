package com.example.ProcessUserRequests.CommandFiles.CustomCommands;

import com.example.ProcessInternalEvents.TodoManager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ViewTodos {

    private TodoManager todoManager;

    public ViewTodos() {
        this.todoManager = new TodoManager();
    }

    public SendMessage sendViewTodos(Long chatId) {

        String viewTodosText = " ";

        if (todoManager.todoListIsEmpty == true) {
            viewTodosText =
                "🤔 Нет добавленных задач.\r\n" +
                "\r\n" +
                "<b>Введите /create_todo для добавления задачи.</b> ➕\r\n";
        }

        else if (todoManager.todoListIsEmpty == false) {
            viewTodosText =
                "⚡️ <b>Вот ваш список задач:</b> 👇\r\n";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(viewTodosText);
        message.setParseMode("HTML");

        return message;

    }

}