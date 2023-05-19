package com.alexsh.bots.tellmescorebot.repository;

import com.alexsh.bots.tellmescorebot.model.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChatRepository extends CrudRepository<Chat, Long> {

    Optional<Chat> findByChatId(Long chatId);
}
