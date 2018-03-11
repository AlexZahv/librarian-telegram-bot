package com.alexzahv.controllers;

import com.alexzahv.business.persistance.domain.Book;
import com.alexzahv.business.services.BookService;
import com.github.alexzahv.springboottelegrambotstarter.AbstractController;
import com.github.alexzahv.springboottelegrambotstarter.annotations.TelegramController;
import com.github.alexzahv.springboottelegrambotstarter.annotations.TelegramMessageMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@TelegramController
public class BookController extends AbstractController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @TelegramMessageMapping("/search")
    public void searchForBooks(Update update) throws TelegramApiException {
        sendMessage(update.getMessage().getChatId(),
                bookService.getBooks(parseRequestString(update.getMessage().getText()), update));
    }

    @TelegramMessageMapping("/get")
    public void getBook(Update update) throws TelegramApiException {
        Book book = bookService.get(parseRequestString(update.getMessage().getText()));
        sendFile(update.getMessage().getChatId(), book.getFileId(), book.getTitle());
    }

    @TelegramMessageMapping("/list")
    public void searchUserBooks(Update update) throws TelegramApiException {
        sendMessage(update.getMessage().getChatId(),
                bookService.getBooks(update.getMessage().getFrom()));

    }

    @TelegramMessageMapping("/description")
    public void addDescription(Update update) throws TelegramApiException {
        bookService.addDescription(update.getMessage().getFrom(),parseRequestString(update.getMessage().getText()));
        sendMessage(update.getMessage().getChatId(),"Description successfully added");
    }

    @TelegramMessageMapping("/author")
    public void addAuthor(Update update) throws TelegramApiException {
        bookService.addAuthor(update.getMessage().getFrom(),parseRequestString(update.getMessage().getText()));
        sendMessage(update.getMessage().getChatId(),"Author successfully added");
    }

}
