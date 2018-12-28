package com.discordia.broomabot.service.impl;

import com.codepoetics.protonpack.StreamUtils;
import com.discordia.broomabot.model.MessageModel;
import com.discordia.broomabot.model.UserModel;
import com.discordia.broomabot.repository.MessageRepository;
import com.discordia.broomabot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class DefaultMessageService implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public String getAllMessages(UserModel user) {
        return StreamUtils.zipWithIndex(messageRepository.findAllByUserAndDeletedFalse(user).stream())
                .map(i -> i.getIndex() + 1 + ") " + i.getValue().getText())
                .collect(Collectors.joining("\n"));
    }

    @Transactional
    public void deleteMessagesForUser(UserModel user) {
        messageRepository.setDeletedByUser(user);
    }

    public void saveMessage(Integer messageId, String receivedMessage, UserModel user) {
        MessageModel messageModel = new MessageModel(new Long(messageId), receivedMessage, user);
        messageRepository.save(messageModel);
    }
}
