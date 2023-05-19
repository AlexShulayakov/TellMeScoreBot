package com.alexsh.bots.tellmescorebot.service;

import com.alexsh.bots.tellmescorebot.data.Discipline;
import com.alexsh.bots.tellmescorebot.data.State;
import com.alexsh.bots.tellmescorebot.model.Chat;
import com.alexsh.bots.tellmescorebot.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.alexsh.bots.tellmescorebot.data.MessageVocabulary.*;
import static com.alexsh.bots.tellmescorebot.data.CommandVocabulary.START_COMMAND;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@Transactional(propagation = REQUIRES_NEW)
public class BotFacade {

    @Autowired
    private ChatRepository chatRepository;

    public BotApiMethod<?> handle(Update update) {
        Message incomingMessage = update.getMessage();
        String text = incomingMessage.getText();
        Long chatId = incomingMessage.getChatId();

        switch (text) {
            case START_COMMAND:
                return handleStart(incomingMessage);
            default:
                return combineResponseMessage(chatId, UNKNOWN_COMMAND_MESSAGE.getText());
        }
    }

    private SendMessage handleStart(Message incomingMessage) {
        Long chatId = incomingMessage.getChatId();
        StringBuilder responseMessage = new StringBuilder();
        Optional<Chat> optionalChat = chatRepository.findByChatId(chatId);
        Chat chat = optionalChat.orElseGet(() -> {
            responseMessage.append(GREETING_MESSAGE.getText());
            return Chat.builder().chatId(chatId).build();
        });
        responseMessage.append(DISCIPLINES_MESSAGE.getText());
        appendDisciplines(responseMessage);
        chat.setState(State.START);
        chatRepository.save(chat);
        return combineResponseMessage(chatId, responseMessage.toString());
    }

    private void appendDisciplines(StringBuilder responseMessage) {

        List<Discipline> disciplines = Arrays.asList(Discipline.values());
        responseMessage.append(disciplines.stream().map(Discipline::toString).collect(Collectors.joining("\n")));
    }

    private SendMessage combineResponseMessage(Long  chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        return sendMessage;
    }
}
