package com.example;
import com.example.GeneralMessages.MessageManager;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@SuppressWarnings("deprecation")
public class TelegramBot extends TelegramLongPollingBot {
    private MessageManager messageManager;

    public TelegramBot() {
        this.messageManager = new MessageManager();
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

            String[] commandParts = messageText.split(" ", 3);
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
                    if (commandParts.length > 2) {
                        changeGeneralMessage(chatId, commandParts[1], commandParts[2]);
                    } else {
                        sendErrorMessage(chatId);
                    }
                    break;

                case "/view_messages":
                    viewMessages(chatId);
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
                        "- /change_message [номер сообщения] - Изменить одно из общих сообщений.\r\n" + //
                        "- /view_messages - Просмотреть все сообщения.\r\n" +
                        "- /create_todo [задача] - Добавить задачу в To-Do список.\r\n" + //
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

    private void sendResponse(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
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