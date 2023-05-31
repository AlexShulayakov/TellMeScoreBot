package com.alexsh.bots.tellmescorebot.model;

import com.alexsh.bots.tellmescorebot.data.CompetitionCode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "competitions")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Competition {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="discipline_id", nullable=false)
    private Discipline discipline;

    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private CompetitionCode code;

    @Column(name = "displayed_name")
    private String displayedName;
}
