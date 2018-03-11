package com.alexzahv.business.services.impl;

import com.alexzahv.business.services.SearchLogService;
import com.alexzahv.business.persistance.domain.SearchLog;
import com.alexzahv.business.persistance.domain.User;
import com.alexzahv.business.persistance.repositories.SearchLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.Message;

import java.time.LocalDateTime;

@Service
public class SearchLogServiceImpl implements SearchLogService {
    @Autowired
    private SearchLogRepository repository;

    @Override
    public SearchLog createRecord(Message message, User user) {
        SearchLog searchLog = new SearchLog();
        searchLog.setCommand(message.getText());
        searchLog.setSearchDate(LocalDateTime.now());
        searchLog.setUser(user);
        return repository.save(searchLog);
    }
}
