package com.example.habittracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private boolean completed;

    @ManyToOne
    private Habit habit;

    public HabitLog() {}

    public HabitLog(Habit habit, LocalDate date) {
        this.habit = habit;
        this.date = date;
        this.completed = false;
    }

    public Long getId() { return id; }

    public LocalDate getDate() { return date; }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public Habit getHabit() { return habit; }
}
