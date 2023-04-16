package com.alexsh.bots.tellmescorebot;

import com.alexsh.bots.tellmescorebot.config.BotConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Component
public class TellMeScoreBot extends SpringWebhookBot {

    @Autowired
    private BotConfiguration configuration;

    public TellMeScoreBot(SetWebhook setWebhook, @Autowired String botToken) {
        super(setWebhook, botToken);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText("Ответ на " + message.getText());
        return sendMessage;
    }

    @Override
    public String getBotPath() {
        return configuration.getBotPath();
    }

    @Override
    public String getBotUsername() {
        return configuration.getBotName();
    }
}
