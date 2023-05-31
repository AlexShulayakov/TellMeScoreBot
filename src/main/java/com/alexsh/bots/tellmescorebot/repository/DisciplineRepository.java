package com.alexsh.bots.tellmescorebot.repository;

import com.alexsh.bots.tellmescorebot.model.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DisciplineRepository {

    @Autowired
    private IDisciplineRepository disciplineRepository;

    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> findByDisplayedName(String displayedName) {
        return disciplineRepository.findByDisplayedName(displayedName);
    }
}
