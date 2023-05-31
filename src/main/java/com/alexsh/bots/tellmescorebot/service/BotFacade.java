package com.alexsh.bots.tellmescorebot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.alexsh.bots.tellmescorebot.data.CommandVocabulary.START_COMMAND;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@Transactional(propagation = REQUIRES_NEW)
public class BotFacade {

    @Autowired
    private BotService botService;

    public BotApiMethod<?> handle(Update update) {
        Message incomingMessage = update.getMessage();
        String text = incomingMessage.getText();
        if (START_COMMAND.equals(text)) {
            return botService.handleStart(incomingMessage);
        } else {
            return botService.handleCommandBasedOnState(incomingMessage);
        }
    }
}
