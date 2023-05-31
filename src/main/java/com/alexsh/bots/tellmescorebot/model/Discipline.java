package com.alexsh.bots.tellmescorebot.model;

import com.alexsh.bots.tellmescorebot.data.DisciplineCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "disciplines")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Discipline {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private DisciplineCode code;

    @Column(name = "displayed_name")
    private String displayedName;

    @OneToMany(mappedBy="discipline")
    private Set<Competition> competitions;
}
