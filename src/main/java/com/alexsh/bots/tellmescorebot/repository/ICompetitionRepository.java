package com.alexsh.bots.tellmescorebot.repository;

import com.alexsh.bots.tellmescorebot.data.CompetitionCode;
import com.alexsh.bots.tellmescorebot.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICompetitionRepository extends JpaRepository<Competition, Long> {

    Optional<Competition> findByCode(CompetitionCode code);
    List<Competition> findByDisciplineId(Long disciplineId);
}
