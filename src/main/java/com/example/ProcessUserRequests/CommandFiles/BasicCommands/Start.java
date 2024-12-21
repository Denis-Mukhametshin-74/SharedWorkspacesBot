package com.example.ProcessUserRequests.CommandFiles.BasicCommands;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Start {

    public SendMessage sendStartMessage(Long chatId) {

        String welcomeText =
            "🌟 Привет! Я здесь, чтобы помочь вам организовать работу и улучшить взаимодействие в команде. 💼✨\r\n" +
            "\r\n" +
            "Что вы хотите сделать?\r\n" +
            "\r\n" +
            "• 📝 <b>Создать задачу:</b> Легко добавляйте и управляйте задачами, чтобы ваша команда всегда знала, что делать. ✅\r\n" +
            "\r\n" +
            "• 💡 <b>Поделиться заметкой:</b> Делитесь идеями и заметками, чтобы каждый мог внести свой вклад. 📚🤝\r\n" +
            "\r\n" +
            "• ⏰ <b>Установить напоминание:</b> Напоминайте о важных событиях и сроках, чтобы ничего не упустить. 🚨📅\r\n" +
            "\r\n" +
            "<b>Используйте команду /help, чтобы узнать больше о доступных командах!</b> 📖🔍\r\n";

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("Общее сообщение").callbackData("/create_message").build());
        row1.add(InlineKeyboardButton.builder().text("To-Do задача").callbackData("/create_todo").build());
    
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("Напоминание").callbackData("/create_reminder").build());
        row2.add(InlineKeyboardButton.builder().text("Помощь").callbackData("/help").build());

        buttons.add(row1);
        buttons.add(row2);

        keyboardMarkup.setKeyboard(buttons);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(welcomeText);
        message.setParseMode("HTML");
        message.setReplyMarkup(keyboardMarkup);

        return message;

    }

}