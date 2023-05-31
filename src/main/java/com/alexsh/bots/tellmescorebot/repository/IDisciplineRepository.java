package com.alexsh.bots.tellmescorebot.repository;

import com.alexsh.bots.tellmescorebot.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDisciplineRepository extends JpaRepository<Discipline, Long> {

    Optional<Discipline> findByDisplayedName(String displayedName);
}
