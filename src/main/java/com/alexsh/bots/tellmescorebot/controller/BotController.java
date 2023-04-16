package com.alexsh.bots.tellmescorebot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BotController {

    @GetMapping
    public ResponseEntity<String> showGreetingMessage() {
        return ResponseEntity.ok("Hello, I'm TellMeScoreBot.");
    }
}
