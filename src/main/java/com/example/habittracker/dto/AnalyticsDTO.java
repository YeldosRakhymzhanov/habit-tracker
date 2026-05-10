package com.example.habittracker.dto;

public class AnalyticsDTO {

    private int totalHabits;
    private int completedToday;
    private int totalCompletions;
    private double completionRate;
    private int longestStreak;

    public AnalyticsDTO(
            int totalHabits,
            int completedToday,
            int totalCompletions,
            double completionRate,
            int longestStreak
    ) {
        this.totalHabits = totalHabits;
        this.completedToday = completedToday;
        this.totalCompletions = totalCompletions;
        this.completionRate = completionRate;
        this.longestStreak = longestStreak;
    }

    public int getTotalHabits() { return totalHabits; }
    public int getCompletedToday() { return completedToday; }
    public int getTotalCompletions() { return totalCompletions; }
    public double getCompletionRate() { return completionRate; }
    public int getLongestStreak() { return longestStreak; }
}