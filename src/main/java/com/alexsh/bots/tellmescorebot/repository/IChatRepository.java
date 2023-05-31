package com.alexsh.bots.tellmescorebot.repository;

import com.alexsh.bots.tellmescorebot.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findByChatId(Long chatId);
}
