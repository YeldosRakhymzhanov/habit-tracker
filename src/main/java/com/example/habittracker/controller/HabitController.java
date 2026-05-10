package com.example.habittracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.habittracker.dto.AnalyticsDTO;
import com.example.habittracker.dto.DayStatDTO;
import com.example.habittracker.dto.HabitDTO;
import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitLog;
import com.example.habittracker.service.HabitService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/habits")
public class HabitController {

    private final HabitService service;

    public HabitController(HabitService service) {
        this.service = service;
    }
    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        return service.saveHabit(habit);
    }
    @GetMapping("/{id}/history")
    public List<HabitLog> getHistory(@PathVariable Long id,  @RequestParam(defaultValue = "30") int days) {
        return service.getHabitHistory(id, days);
    }
    @DeleteMapping("/{id}")
        public void deleteHabit(@PathVariable Long id) {
        service.deleteHabit(id);
    }
    @PutMapping("/{id}/toggle")
    public Habit toggleHabit(@PathVariable Long id) {
        return service.toggleCompleted(id);
    }
    @PutMapping("/{id}/today")
    public HabitLog toggleToday(@PathVariable Long id) {
        return service.toggleToday(id);
    }
    @GetMapping
    public List<HabitDTO> getHabits() {
        return service.getHabitsWithTodayStatus();
    }
    @GetMapping("/analytics")
    public AnalyticsDTO analytics() {
        return service.getAnalytics();
    }
    @GetMapping("/weekly")
    public List<DayStatDTO> weeklyStats() {
        return service.getWeeklyStats();
    }
}