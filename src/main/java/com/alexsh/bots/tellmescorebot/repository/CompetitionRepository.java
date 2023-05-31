package com.alexsh.bots.tellmescorebot.repository;

import com.alexsh.bots.tellmescorebot.data.CompetitionCode;
import com.alexsh.bots.tellmescorebot.model.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompetitionRepository {

    @Autowired
    private ICompetitionRepository competitionRepository;

    public Optional<Competition> findByCode(CompetitionCode code) {
        return competitionRepository.findByCode(code);
    }

    public List<Competition> findByDisciplineId(Long disciplineId) {
        return competitionRepository.findByDisciplineId(disciplineId);
    }
}
