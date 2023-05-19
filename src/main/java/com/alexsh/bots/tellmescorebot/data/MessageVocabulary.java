package com.alexsh.bots.tellmescorebot.data;

import lombok.Getter;

@Getter
public enum MessageVocabulary {
    GREETING_MESSAGE("Приветствую вас!\nЯ могу рассказать о результатах поледних спортивных состязаний.\n"),
    DISCIPLINES_MESSAGE("Виберите интересующую спортивную дисциплину:\n"),
    UNKNOWN_COMMAND_MESSAGE("Извините, я не понимаю.");

    private final String text;

    MessageVocabulary(String text) {
        this.text = text;
    }
}
