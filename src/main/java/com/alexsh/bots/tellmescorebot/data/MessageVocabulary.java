package com.alexsh.bots.tellmescorebot.data;

import lombok.Getter;

@Getter
public enum MessageVocabulary {
    GREETING_MESSAGE("Приветствую вас!\nЯ могу рассказать о результатах поледних спортивных состязаний.\n"),
    DISCIPLINES_MESSAGE("Виберите интересующую спортивную дисциплину:\n"),
    COMPETITIONS_MESSAGE("Виберите интересующий турнир:\n"),
    NO_COMPETITIONS_MESSAGE("Извините, у меня пока нет информации о турнирах в этой дисциплине."),
    UNKNOWN_DISCIPLINE_MESSAGE("Извините, я не знаю такой дисциплины."),
    UNKNOWN_COMPETITION_MESSAGE("Извините, я не знаю такой турнир."),
    UNKNOWN_COMMAND_MESSAGE("Извините, я не понимаю.");

    private final String text;

    MessageVocabulary(String text) {
        this.text = text;
    }
}
