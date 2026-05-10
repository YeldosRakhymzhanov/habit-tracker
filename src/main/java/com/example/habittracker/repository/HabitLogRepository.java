package com.example.habittracker.repository;

import com.example.habittracker.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    
    Optional<HabitLog> findByHabitIdAndDate(Long habitId, LocalDate date);
    List<HabitLog> findByHabitId(Long habitId);
}

