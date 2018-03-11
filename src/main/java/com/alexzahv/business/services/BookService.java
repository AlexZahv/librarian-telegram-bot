package com.alexzahv.business.services;

import com.alexzahv.business.persistance.domain.Book;
import com.github.alexzahv.springboottelegrambotstarter.handlers.TelegramRequestString;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

public interface BookService {
    String getBooks(TelegramRequestString request, Update update);

    String getBooks(org.telegram.telegrambots.api.objects.User user);

    Book add(Message message);

    Book get(TelegramRequestString request);

    void addDescription(User user, TelegramRequestString request);

    void addAuthor(User user, TelegramRequestString request);
}
