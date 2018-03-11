package com.alexzahv.controllers;

import com.alexzahv.business.services.BookService;
import com.github.alexzahv.springboottelegrambotstarter.handlers.TelegramBotHandler;
import com.github.alexzahv.springboottelegrambotstarter.handlers.UploadFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class LibrarianUploadFileHandler implements UploadFileHandler {
    private final TelegramBotHandler botHandler;
    private final BookService bookService;

    @Autowired
    public LibrarianUploadFileHandler(TelegramBotHandler botHandler, BookService bookService) {
        this.botHandler = botHandler;
        this.bookService = bookService;
    }

    @Override
    public void handleUpload(Update update) throws TelegramApiException {
        bookService.add(update.getMessage());
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Thank you, we successfully added book to our library. Please use /get " +
                "to download it");
        botHandler.sendMessage(message);
    }
}
