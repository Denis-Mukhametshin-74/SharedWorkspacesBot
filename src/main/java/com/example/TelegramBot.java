package com.example;
import com.example.GeneralMessages.MessageManager;
import com.example.ToDoLists.TodoManager;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@SuppressWarnings("deprecation")
public class TelegramBot extends TelegramLongPollingBot {
    private MessageManager messageManager;
    private TodoManager todoManager;

    public TelegramBot() {
        this.messageManager = new MessageManager();
        this.todoManager = new TodoManager();
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            String[] commandParts = messageText.split(" ", 2);
            switch (commandParts[0]) {
                case "/start":
                    sendStartMessage(chatId);
                    break;

                case "/help":
                    sendHelpMessage(chatId);
                    break;

                case "/create_message":
                    if (commandParts.length > 1) {
                        createGeneralMessage(chatId, commandParts[1]);
                    } else {
                        sendErrorMessage(chatId);
                    }
                    break;

                case "/change_message":
                    if (commandParts.length <= 1) {
                        sendErrorMessage(chatId);
                    } else {
                        String[] changeParts = commandParts[1].split(" ", 2);
                        if (changeParts.length > 1) {
                            changeGeneralMessage(chatId, changeParts[0], changeParts[1]);
                        } else {
                            sendErrorMessage(chatId);
                        }
                    }
                    break;

                case "/delete_message":
                    if (commandParts.length > 1) {
                        deleteGeneralMessage(chatId, commandParts[1]);
                    } else {
                        sendErrorMessage(chatId);
                    }
                    break;

                case "/view_messages":
                    viewMessages(chatId);
                    break;

                case "/create_todo":
                    handleCreateTodo(chatId, commandParts);
                    break;

                case "/status_todo":
                    handleChangeStatus(chatId, commandParts);
                    break;

                case "/view_todos":
                    viewTodos(chatId);
                    break;

                case "/delete_todo":
                    handleDeleteTodo(chatId, commandParts);
                    break;

                default:
                    sendErrorMessage(chatId);
                    break;
            }
        }
    }

    private void createGeneralMessage(Long chatId, String text) {
        messageManager.createMessage(text);
        String responseText = "Сообщение создано: " + text;
        sendResponse(chatId, responseText);
    }

    private void changeGeneralMessage(Long chatId, String indexStr, String newText) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (messageManager.changeMessage(index, newText)) {
                String responseText = "Сообщение изменено: " + newText;
                sendResponse(chatId, responseText);
            } else {
                sendErrorMessage(chatId);
            }
        } catch (NumberFormatException e) {
            sendErrorMessage(chatId);
        }
    }

    private void deleteGeneralMessage(Long chatId, String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (messageManager.deleteMessage(index)) {
                String responseText = "Сообщение удалено.";
                sendResponse(chatId, responseText);
            } else {
                sendErrorMessage(chatId);
            }
        } catch (NumberFormatException e) {
            sendErrorMessage(chatId);
        }
    }

    private void viewMessages(Long chatId) {
        List<String> messages = messageManager.getMessages();
        if (messages.isEmpty()) {
            sendResponse(chatId, "Нет созданных сообщений.");
        } else {
            StringBuilder responseText = new StringBuilder("Список сообщений:\n");
            for (int i = 0; i < messages.size(); i++) {
                responseText.append(i + 1).append(": ").append(messages.get(i)).append("\n");
            }
            sendResponse(chatId, responseText.toString());
        }
    }

    private void sendStartMessage(Long chatId) {
        String welcomeText = "Привет! Я ваш помощник по созданию общих рабочих пространств. С помощью меня вы можете:\r\n" + //
                        "- Создавать и управлять общими сообщениями\r\n" + //
                        "- Формировать To-Do списки для командной работы\r\n" + //
                        "- Делиться заметками и идеями\r\n" + //
                        "\r\n" + //
                        "Используйте команду /help, чтобы узнать больше о доступных командах!\r\n" + //
                        "";
        sendResponse(chatId, welcomeText);
    }

    private void sendHelpMessage(Long chatId) {
        String helpText = "Вот список доступных команд:\r\n" + //
                        "- /create_message [текст] - Создать общее сообщение для вашей команды.\r\n" + //
                        "- /change_message [номер сообщения] [текст] - Изменить одно из общих сообщений.\r\n" + //
                        "- /delete_message [номер сообщения] - Удалить одно из общих сообщений.\r\n" + //
                        "- /view_messages - Просмотреть все сообщения.\r\n" +
                        "- /create_todo [задача] - Добавить задачу в To-Do список.\r\n" + //
                        "- /status_todo [номер задачи] - Изменить текущий статус выполнения задачи.\r\n" + //
                        "- /view_todos - Просмотреть текущий To-Do список.\r\n" + //
                        "- /delete_todo [номер задачи] - Удалить задачу из To-Do списка.\r\n" + //
                        "\r\n" + //
                        "Если у вас есть вопросы или нужна помощь, обратитесь к администратору!\r\n" + //
                        "";
        sendResponse(chatId, helpText);
    }

    private void sendErrorMessage(Long chatId) {
        String errorText = "Ошибка: Неправильная команда\r\n" + //
                        "\r\n" + //
                        "Вы ввели команду, которая не распознана ботом. Пожалуйста, убедитесь, что ваша команда написана правильно и соответствует одному из доступных форматов.\r\n" + //
                        "\r\n" + //
                        "Проверьте список доступных команд, введя /help, чтобы увидеть все доступные функции бота.\r\n" + //
                        "";
        sendResponse(chatId, errorText);
    }

    private void handleCreateTodo(Long chatId, String[] commandParts) {
        if (checkCommandParameters(commandParts, 1)) {
            String task = commandParts[1];
            todoManager.addTask(task);
            sendResponse(chatId, "Задача добавлена: " + task);
        } else {
            sendErrorMessage(chatId);
        }
    }

    private void handleChangeStatus(Long chatId, String[] commandParts) {
        if (checkCommandParameters(commandParts, 1)) {
            try {
                int taskNumber = Integer.parseInt(commandParts[1]) - 1;
                String response = todoManager.changeTaskStatus(taskNumber);
                sendResponse(chatId, response);
            } catch (NumberFormatException e) {
                sendErrorMessage(chatId);
            }
        } else {
            sendErrorMessage(chatId);
        }
    }    

    private void viewTodos(Long chatId) {
        String response = todoManager.viewTasks();
        sendResponse(chatId, response);
    }

    private void handleDeleteTodo(Long chatId, String[] commandParts) {
        if (checkCommandParameters(commandParts, 1)) {
            try {
                int taskNumber = Integer.parseInt(commandParts[1]) - 1;
                String response = todoManager.deleteTask(taskNumber);
                sendResponse(chatId, response);
            } catch (NumberFormatException e) {
                sendErrorMessage(chatId);
            }
        } else {
            sendErrorMessage(chatId);
        }
    }

    private boolean checkCommandParameters(String[] commandParts, int expectedLength) {
        return commandParts.length > expectedLength;
    }

    private void sendResponse(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setParseMode("HTML");
        executeWithExceptionHandling(message);
    }

    private void executeWithExceptionHandling(SendMessage message) {
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}