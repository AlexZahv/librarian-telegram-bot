package com.alexzahv.business.services.impl;

import com.alexzahv.business.persistance.domain.Book;
import com.alexzahv.business.persistance.domain.User;
import com.alexzahv.business.persistance.repositories.BookRepository;
import com.alexzahv.business.services.SearchLogService;
import com.alexzahv.business.services.UserService;
import com.alexzahv.business.services.BookService;
import com.github.alexzahv.springboottelegrambotstarter.handlers.TelegramRequestString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.Document;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Collection;
import java.util.Set;

import static java.util.Optional.ofNullable;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final UserService userService;
    private final SearchLogService searchLogService;

    @Autowired
    public BookServiceImpl(BookRepository repository, UserService userService, SearchLogService searchLogService) {
        this.repository = repository;
        this.userService = userService;
        this.searchLogService = searchLogService;
    }

    @Override
    public String getBooks(TelegramRequestString request, Update update) {
        String message = request.getMessage().trim().toLowerCase();
        searchLogService.createRecord(update.getMessage(), userService.getOrCreate(update.getMessage().getFrom()));
        Set<Book> books = repository.findAllByTitleContaining(message);
        for (String part : message.split(" ")) {
            books.addAll(repository.findAllByTitleContaining(part));
            books.addAll(repository.findAllByAuthorContaining(part));
            books.addAll(repository.findAllByDescriptionContaining(part));
        }
        return formatOutput(books);

    }

    @Override
    public String getBooks(org.telegram.telegrambots.api.objects.User user) {
        User presistedUser = userService.getOrCreate(user);
        return formatOutput(repository.findAllByUser(presistedUser));
    }

    private String formatOutput(Collection<Book> books) {
        int lineNumber = 1;
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(String.format("%d. %s, id: %d \n", lineNumber++, book.getTitle(), book.getId()));
        }
        return sb.toString();
    }

    @Override
    public Book add(Message message) {
        Document document = message.getDocument();
        User user = userService.getOrCreate(message.getFrom());
        Book book = new Book();
        book.setFileId(document.getFileId());
        book.setMimeType(document.getMimeType());
        book.setSize(document.getFileSize().longValue());
        book.setTitle(document.getFileName().toLowerCase());
        book.setUser(user);
        return repository.save(book);
    }

    @Override
    public Book get(TelegramRequestString request) {
        return repository.findOne(Long.parseLong(request.getMessage().trim()));
    }

    @Override
    public void addDescription(org.telegram.telegrambots.api.objects.User user, TelegramRequestString request) {
        ofNullable(findLastByUser(user)).ifPresent(book -> {
            book.setDescription(request.getMessage().toLowerCase());
            repository.save(book);
        });

    }

    @Override
    public void addAuthor(org.telegram.telegrambots.api.objects.User user, TelegramRequestString request) {
        ofNullable(findLastByUser(user)).ifPresent(book -> {
            book.setAuthor(request.getMessage().toLowerCase());
            repository.save(book);
        });
    }

    private Book findLastByUser(org.telegram.telegrambots.api.objects.User user) {
        User persistedUser = userService.getOrCreate(user);
        return repository.findFirstByUserOrderByCreateDateDesc(persistedUser);
    }

}
