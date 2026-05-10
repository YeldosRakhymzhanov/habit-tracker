package com.example.habittracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.habittracker.dto.AnalyticsDTO;
import com.example.habittracker.dto.DayStatDTO;
import com.example.habittracker.dto.HabitDTO;
import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitLog;
import com.example.habittracker.repository.HabitLogRepository;
import com.example.habittracker.repository.HabitRepository;

import java.time.format.TextStyle;
import java.util.Locale;
@Service
public class HabitService {

    private final HabitRepository repository;
    private final HabitLogRepository logRepository;

    public HabitService(HabitRepository repository, HabitLogRepository logRepository) {
        this.repository = repository;
        this.logRepository = logRepository;
    }

    public Habit saveHabit(Habit habit) {
        return repository.save(habit);
    }

    public List<Habit> getAllHabits() {
        return repository.findAll();
    }
    public void deleteHabit(Long id) {
        repository.deleteById(id);
    }
    public Habit toggleCompleted(Long id) {
        Habit habit = repository.findById(id).orElseThrow();
        habit.setCompleted(!habit.isCompleted());
        return repository.save(habit);
    }
    public HabitLog toggleToday(Long habitId) {
        LocalDate today = LocalDate.now();

        HabitLog log = logRepository
            .findByHabitIdAndDate(habitId, today)
            .orElseGet(() -> {
                Habit habit = repository.findById(habitId).orElseThrow();
                return new HabitLog(habit, today);
            });

        log.setCompleted(!log.isCompleted());
        return logRepository.save(log);
    }
    public List<HabitDTO> getHabitsWithTodayStatus() {
        LocalDate today = LocalDate.now();

        return repository.findAll().stream().map(habit -> {
            boolean completed = logRepository
                .findByHabitIdAndDate(habit.getId(), today)
                .map(HabitLog::isCompleted)
                .orElse(false);

            int streak = calculateStreak(habit.getId());

            return new HabitDTO(
                habit.getId(),
                habit.getName(),
                completed,
                streak
            );
        }).toList();
    }
    public int calculateStreak(Long habitId) {
        int streak = 0;
        LocalDate date = LocalDate.now();

        while (true) {
            boolean completed = logRepository
                .findByHabitIdAndDate(habitId, date)
                .map(HabitLog::isCompleted)
                .orElse(false);

            if (!completed) break;

            streak++;
            date = date.minusDays(1);
        }

        return streak;
    }
    public List<HabitLog> getHabitHistory(Long habitId, int days) {
        return logRepository.findByHabitId(habitId);
    }

    public AnalyticsDTO getAnalytics() {

        List<Habit> habits = repository.findAll();
        List<HabitLog> logs = logRepository.findAll();

        int totalHabits = habits.size();

        int completedToday = (int) logs.stream()
                .filter(log ->
                        log.getDate().equals(LocalDate.now()) &&
                        log.isCompleted())
                .count();

        int totalCompletions = (int) logs.stream()
                .filter(HabitLog::isCompleted)
                .count();

        int longestStreak = habits.stream()
                .mapToInt(h -> calculateLongestStreak(h.getId()))
                .max()
                .orElse(0);

        double completionRate = logs.isEmpty()
                ? 0
                : ((double) totalCompletions / logs.size()) * 100;

        return new AnalyticsDTO(
                totalHabits,
                completedToday,
                totalCompletions,
                Math.round(completionRate),
                longestStreak
        );
    }

    public int calculateLongestStreak(Long habitId) {

        List<HabitLog> logs = logRepository.findByHabitId(habitId)
                .stream()
                .filter(HabitLog::isCompleted)
                .sorted((a, b) -> a.getDate().compareTo(b.getDate()))
                .toList();

        int best = 0;
        int current = 0;

        LocalDate prev = null;

        for (HabitLog log : logs) {

            if (prev != null &&
                prev.plusDays(1).equals(log.getDate())) {

                current++;

            } else {

                current = 1;
            }

            best = Math.max(best, current);
            prev = log.getDate();
        }

        return best;
    }
    public List<DayStatDTO> getWeeklyStats() {
        List<DayStatDTO> result = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {

            LocalDate date = LocalDate.now().minusDays(i);

            int count = (int) logRepository.findAll()
                    .stream()
                    .filter(log ->
                            log.getDate().equals(date) &&
                            log.isCompleted())
                    .count();

            String day = date.getDayOfWeek()
                    .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

            result.add(new DayStatDTO(day, count));
        }

        return result;
    }
}

