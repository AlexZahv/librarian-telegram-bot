package com.alexzahv.business.services.impl;

import com.alexzahv.business.persistance.domain.User;
import com.alexzahv.business.persistance.repositories.UserRepository;
import com.alexzahv.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User find(Long id) {
        return repository.findOne(id);
    }

    @Override
    public User create(org.telegram.telegrambots.api.objects.User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setId(Long.valueOf(user.getId()));
        newUser.setLastName(user.getLastName());
        newUser.setUserName(user.getUserName());
        return repository.save(newUser);
    }

    @Override
    public User getOrCreate(org.telegram.telegrambots.api.objects.User user) {
        return ofNullable(repository.findOne(user.getId().longValue())).orElse(create(user));
    }
}
