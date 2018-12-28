package com.discordia.broomabot;

import com.discordia.broomabot.model.UserModel;
import com.discordia.broomabot.service.MessageService;
import com.discordia.broomabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
public class Broomabot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    @Transactional
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String receivedMessage = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            UserModel user =
                    userService.getUser(chatId).orElseGet(() -> userService.createUser(update.getMessage().getFrom()));

            createSendMessage(update.getUpdateId(), receivedMessage, user).ifPresent(sendMessage -> {
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private Optional<SendMessage> createSendMessage(Integer receivedMessageId, String receivedMessage, UserModel user) {
        return Optional.ofNullable(makeResponseText(receivedMessageId, receivedMessage, user))
                .map(text -> new SendMessage().setChatId(user.getId()).setText(text));
    }

    private String makeResponseText(Integer receivedMessageId, String receivedMessage, UserModel user) {
        if (!user.isChatStarted()) {
            if ("/start".equals(receivedMessage)) {
                userService.startChat(user);
                return "Добро пожаловать!";
            }

            return null;
        }

        if ("/start".equals(receivedMessage)) {
            return null;
        }

        if ("/my".equals(receivedMessage)) {
            String allMessages = messageService.getAllMessages(user);
            return allMessages.isEmpty() ? "Список пуст" : "Список ваших сообщений:\n" + allMessages;
        }

        if ("/clean".equals(receivedMessage)) {
            messageService.deleteMessagesForUser(user);
            return "Сообщения очищены";
        }

        messageService.saveMessage(receivedMessageId, receivedMessage, user);

        return "Ваше сообщение записано";
    }
}
