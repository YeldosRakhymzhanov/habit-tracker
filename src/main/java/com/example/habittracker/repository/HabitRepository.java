package com.example.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.habittracker.model.Habit;

public interface HabitRepository extends JpaRepository<Habit, Long> {
}