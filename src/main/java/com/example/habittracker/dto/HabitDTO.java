package com.example.habittracker.dto;

public class HabitDTO {

    private Long id;
    private String name;
    private boolean completedToday;
    private int streak;

    public HabitDTO(Long id, String name, boolean completedToday, int streak) {
        this.id = id;
        this.name = name;
        this.completedToday = completedToday;
        this.streak = streak;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public boolean isCompletedToday() { return completedToday; }

    public void setCompletedToday(boolean completedToday) {
        this.completedToday = completedToday;
    }
}