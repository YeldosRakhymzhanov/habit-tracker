package com.example.habittracker.controller;

import com.example.habittracker.model.User;
import com.example.habittracker.service.AuthService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {

        boolean ok = service.login(user.getUsername(), user.getPassword());

        if (ok) {
            session.setAttribute("user", user.getUsername());
            return "Login successful";
        }

        return "Invalid credentials";
    }
}