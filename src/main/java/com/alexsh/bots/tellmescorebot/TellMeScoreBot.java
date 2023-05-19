package com.alexsh.bots.tellmescorebot;

import com.alexsh.bots.tellmescorebot.config.BotConfiguration;
import com.alexsh.bots.tellmescorebot.service.BotFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Component
public class TellMeScoreBot extends SpringWebhookBot {

    @Autowired
    private BotConfiguration configuration;

    @Autowired
    private BotFacade botFacade;

    public TellMeScoreBot(SetWebhook setWebhook, @Autowired String botToken) {
        super(setWebhook, botToken);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return botFacade.handle(update);
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
