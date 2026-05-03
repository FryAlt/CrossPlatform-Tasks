package org.example.tasks_123.service;

import org.example.tasks_123.dto.RegistrationDto;
import org.example.tasks_123.entity.User;
import org.example.tasks_123.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void registerSuccess() {
        RegistrationDto dto = new RegistrationDto("akiro_dev", "aki@code.com", "StrongPass1");
        when(userRepository.existsByUsername(dto.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(userRepository.save(any())).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId(1L);
            return u;
        });

        User saved = userService.register(dto);
        assertNotNull(saved);
        verify(userRepository).save(any());
    }

    @Test
    void registerWeakPassword() {
        RegistrationDto dto = new RegistrationDto("akiro", "a@b.com", "weak");
        assertThrows(IllegalArgumentException.class, () -> userService.register(dto));
    }

    @Test
    void registerDuplicateUsername() {
        RegistrationDto dto = new RegistrationDto("taken", "new@b.com", "Strong1");
        when(userRepository.existsByUsername("taken")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> userService.register(dto));
        verify(userRepository, never()).save(any());
    }
}