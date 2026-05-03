package org.example.tasks_123.controller;

import jakarta.validation.Valid;
import org.example.tasks_123.dto.RegistrationDto;
import org.example.tasks_123.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public String authPage(Model model) {
        model.addAttribute("registrationDto", new RegistrationDto());
        return "auth";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegistrationDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) return "auth";
        try {
            userService.register(dto);
            return "redirect:/?registered=true";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "auth";
        }
    }
}