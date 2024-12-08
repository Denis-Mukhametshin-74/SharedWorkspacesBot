package com.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@SuppressWarnings("deprecation")
public class Bot extends TelegramLongPollingBot {

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

            switch (messageText) {
                case "/start":
                    sendStartMessage(chatId);
                    break;

                case "/help":
                    sendHelpMessage(chatId);
                    break;

                default:
                    sendErrorMessage(chatId);
                    break;
            }
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
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(welcomeText);
        executeWithExceptionHandling(message);
    }

    private void sendHelpMessage(Long chatId) {
        String helpText = "Вот список доступных команд:\r\n" + //
                        "- /create_message [текст] - Создать общее сообщение для вашей команды.\r\n" + //
                        "- /change_message [номер сообщения] - Изменить одно из общих сообщений.\r\n" + //
                        "- /create_todo [задача] - Добавить задачу в To-Do список.\r\n" + //
                        "- /view_todos - Просмотреть текущий To-Do список.\r\n" + //
                        "- /delete_todo [номер задачи] - Удалить задачу из To-Do списка.\r\n" + //
                        "\r\n" + //
                        "Если у вас есть вопросы или нужна помощь, обратитесь к администратору!\r\n" + //
                        "";
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(helpText);
        executeWithExceptionHandling(message);
    }

    private void sendErrorMessage(Long chatId) {
        String welcomeText = "Ошибка: Неправильная команда\r\n" + //
                        "\r\n" + //
                        "Вы ввели команду, которая не распознана ботом. Пожалуйста, убедитесь, что ваша команда написана правильно и соответствует одному из доступных форматов.\r\n" + //
                        "\r\n" + //
                        "Проверьте список доступных команд, введя /help, чтобы увидеть все доступные функции бота.\r\n" + //
                        "";
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(welcomeText);
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