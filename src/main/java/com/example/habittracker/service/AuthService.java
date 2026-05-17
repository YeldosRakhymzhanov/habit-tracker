package com.example.habittracker.service;

import com.example.habittracker.model.User;
import com.example.habittracker.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public String register(User user) {

        if (repository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        repository.save(user);

        return "User registered";
    }

    public boolean login(String username, String password) {

        return repository.findByUsername(username)
                .map(user ->
                        encoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}