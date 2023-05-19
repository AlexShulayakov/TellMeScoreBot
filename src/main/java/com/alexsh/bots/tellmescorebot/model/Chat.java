package com.alexsh.bots.tellmescorebot.model;

import com.alexsh.bots.tellmescorebot.data.State;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chats")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Chat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    @Column(name = "context")
    private String context;
}
