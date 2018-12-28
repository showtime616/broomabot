package com.discordia.broomabot.service;

import com.discordia.broomabot.model.UserModel;

public interface MessageService {
    String getAllMessages(UserModel user);
    void deleteMessagesForUser(UserModel user);
    void saveMessage(Integer messageId, String receivedMessage, UserModel user);
}
