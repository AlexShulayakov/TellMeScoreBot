package com.alexsh.bots.tellmescorebot.controller;

import com.alexsh.bots.tellmescorebot.TellMeScoreBot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.alexsh.bots.tellmescorebot.TestUtil.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BotController.class)
class BotControllerTest {

    public static final Long CHAT_ID_LONG = 100L;

    public static final String CHAT_ID_STRING = String.valueOf(CHAT_ID_LONG);
    public static final String MESSAGE_TEXT = "message text";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TellMeScoreBot bot;

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void onUpdateReceivedThenCallBotMethodAndReturnMessage() throws Exception {
        //Given
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(CHAT_ID_LONG);
        message.setChat(chat);
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CHAT_ID_STRING);
        sendMessage.setText(MESSAGE_TEXT);

        //When
        when(bot.onWebhookUpdateReceived(update)).thenReturn((BotApiMethod) sendMessage);

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/")
                        .content(asJsonString(update))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.chat_id").value(CHAT_ID_STRING))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(MESSAGE_TEXT));

        //Then
        verify(bot, times(1)).onWebhookUpdateReceived(update);
    }
}
