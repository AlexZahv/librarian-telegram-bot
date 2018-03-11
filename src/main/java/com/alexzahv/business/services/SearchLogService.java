package com.alexzahv.business.services;

import com.alexzahv.business.persistance.domain.SearchLog;
import com.alexzahv.business.persistance.domain.User;
import org.telegram.telegrambots.api.objects.Message;

public interface SearchLogService {
    SearchLog createRecord(Message message, User user);
}
