package com.alexsh.bots.tellmescorebot.controller;

import com.alexsh.bots.tellmescorebot.TellMeScoreBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequestMapping("/")
public class BotController {

    @Autowired
    private TellMeScoreBot bot;

    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }

    @GetMapping
    public ResponseEntity<String> showGreetingMessage() {
        return ResponseEntity.ok("Hello, I'm TellMeScoreBot.");
    }
}
