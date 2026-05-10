package com.example.habittracker.dto;

public class DayStatDTO {

    private String day;
    private int completed;

    public DayStatDTO(String day, int completed) {
        this.day = day;
        this.completed = completed;
    }

    public String getDay() { return day; }

    public int getCompleted() { return completed; }
}