package com.discordia.broomabot.service.impl;

import com.discordia.broomabot.model.UserModel;
import com.discordia.broomabot.repository.UserRepository;
import com.discordia.broomabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@Service
public class DefaultUserService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserModel> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserModel createUser(User from) {
        UserModel userModel = new UserModel(new Long(from.getId()), from.getFirstName(), from.getLastName(),
                from.getUserName());

        return userRepository.save(userModel);
    }

    @Override
    public void startChat(UserModel user) {
        user.setChatStarted(true);
        userRepository.save(user);
    }
}
