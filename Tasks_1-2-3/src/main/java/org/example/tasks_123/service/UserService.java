package org.example.tasks_123.service;

import org.example.tasks_123.dto.RegistrationDto;
import org.example.tasks_123.entity.User;
import org.example.tasks_123.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;

    private static final Pattern PASSWORD_COMPLEXITY = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(RegistrationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername()))
            throw new IllegalArgumentException("Логин уже занят");
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("Email уже зарегистрирован");
        if (!PASSWORD_COMPLEXITY.matcher(dto.getPassword()).matches())
            throw new IllegalArgumentException("Пароль должен содержать 8+ символов, заглавную, строчную букву и цифру");

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // В реальном проекте: passwordEncoder.encode()
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        if (!user.getPassword().equals(password))
            throw new IllegalArgumentException("Неверный пароль");
        return user;
    }
}
