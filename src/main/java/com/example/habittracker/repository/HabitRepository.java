package com.example.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.habittracker.model.Habit;
import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUsername(String username);
}