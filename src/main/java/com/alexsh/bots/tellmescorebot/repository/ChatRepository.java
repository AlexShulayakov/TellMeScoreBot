package com.alexsh.bots.tellmescorebot.repository;

import com.alexsh.bots.tellmescorebot.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChatRepository {
    @Autowired
    private IChatRepository chatRepository;

    public void save(Chat chat) {
        chatRepository.save(chat);
    }

    public Optional<Chat> findByChatId(Long chatId) {
        return chatRepository.findByChatId(chatId);
    }
}
