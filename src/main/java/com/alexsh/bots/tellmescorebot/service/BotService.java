package com.alexsh.bots.tellmescorebot.service;

import com.alexsh.bots.tellmescorebot.data.CompetitionCode;
import com.alexsh.bots.tellmescorebot.data.State;
import com.alexsh.bots.tellmescorebot.model.Chat;
import com.alexsh.bots.tellmescorebot.model.Competition;
import com.alexsh.bots.tellmescorebot.model.Discipline;
import com.alexsh.bots.tellmescorebot.repository.ChatRepository;
import com.alexsh.bots.tellmescorebot.repository.CompetitionRepository;
import com.alexsh.bots.tellmescorebot.repository.DisciplineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.alexsh.bots.tellmescorebot.data.MessageVocabulary.*;

@Service
public class BotService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    public SendMessage handleStart(Message incomingMessage) {
        Long chatId = incomingMessage.getChatId();
        Optional<Chat> optionalChat = chatRepository.findByChatId(chatId);
        StringBuilder responseMessage = new StringBuilder();
        Chat chat = optionalChat.orElseGet(() -> {
            responseMessage.append(GREETING_MESSAGE.getText());
            return Chat.builder().chatId(chatId).build();
        });
        responseMessage.append(DISCIPLINES_MESSAGE.getText());
        responseMessage.append(disciplineRepository.findAll().stream().map(Discipline::getDisplayedName).collect(Collectors.joining("\n")));
        chat.setState(State.INITIAL);
        chatRepository.save(chat);
        return combineResponseMessage(chatId, responseMessage.toString());
    }

    public SendMessage handleCommandBasedOnState(Message incomingMessage) {
        Long chatId = incomingMessage.getChatId();
        Optional<Chat> optionalChat = chatRepository.findByChatId(chatId);
        if (optionalChat.isEmpty()) {
            return handleStart(incomingMessage);
        } else {
            try {
                Chat chat = optionalChat.get();
                State state = chat.getState();
                switch (state) {
                    case INITIAL:
                        return selectDiscipline(chat, incomingMessage.getText());
                    case DISCIPLINE_SELECTED:
                        return selectCompetition(chat, incomingMessage.getText());
                    default:
                        //TODO
                        return combineResponseMessage(chatId, UNKNOWN_COMMAND_MESSAGE.getText());
                }
            }
            catch (JsonProcessingException e) {
                //TODO
                return combineResponseMessage(chatId, UNKNOWN_COMMAND_MESSAGE.getText());
            }
        }
    }

    private SendMessage selectDiscipline(Chat chat, String typedDiscipline) throws JsonProcessingException {
        Optional<Discipline> discipline = disciplineRepository.findByDisplayedName(typedDiscipline);
        if (discipline.isPresent()) {
            List<Competition> competitions = competitionRepository.findByDisciplineId(discipline.get().getId());
            if (!competitions.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                String context = mapper.writeValueAsString(discipline.get());
                chat.setContext(context);
                chat.setState(State.DISCIPLINE_SELECTED);
                chatRepository.save(chat);

                StringBuilder responseMessage = new StringBuilder();
                responseMessage.append(COMPETITIONS_MESSAGE.getText());
                responseMessage.append(competitions.stream().map(competition -> competition.getCode() + " / " + competition.getDisplayedName())
                        .collect(Collectors.joining("\n")));
                return combineResponseMessage(chat.getChatId(), responseMessage.toString());
            } else {
                return combineResponseMessage(chat.getChatId(), NO_COMPETITIONS_MESSAGE.getText());
            }
        } else {
            //TODO
            return combineResponseMessage(chat.getChatId(), UNKNOWN_DISCIPLINE_MESSAGE.getText());
        }
    }

    private SendMessage selectCompetition(Chat chat, String typedCompetitionCode) throws JsonProcessingException {

        CompetitionCode code = CompetitionCode.valueOf(typedCompetitionCode.toUpperCase());
        Optional<Competition> competition = competitionRepository.findByCode(code);
        if (competition.isPresent()) {
            ObjectMapper mapper = new ObjectMapper();
            String context = mapper.writeValueAsString(competition.get());
            chat.setContext(context);
            chat.setState(State.COMPETITION_SELECTED);
            chatRepository.save(chat);
            return combineResponseMessage(chat.getChatId(), "Вы выбрали " + competition.get().getDisplayedName());
        } else {
            //TODO
            return combineResponseMessage(chat.getChatId(), UNKNOWN_COMPETITION_MESSAGE.getText());
        }
    }

    private SendMessage combineResponseMessage(Long  chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        return sendMessage;
    }
}
