package com.example.oca42.controller;

import com.example.oca42.model.AuthRequest;
import com.example.oca42.model.AuthResponse;
import com.example.oca42.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping
    public AuthResponse authenticate(@RequestBody @Valid AuthRequest request) {
        return authService.authenticate(request);
    }

}