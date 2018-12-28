package com.discordia.broomabot.service;

import com.discordia.broomabot.model.UserModel;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

public interface UserService {
    Optional<UserModel> getUser(Long id);
    UserModel createUser(User from);
    void startChat(UserModel user);
}
