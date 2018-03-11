package com.alexzahv.business.services;

import com.alexzahv.business.persistance.domain.User;

public interface UserService {
    User find(Long id);

    User create(org.telegram.telegrambots.api.objects.User user);

    User getOrCreate(org.telegram.telegrambots.api.objects.User user);
}
