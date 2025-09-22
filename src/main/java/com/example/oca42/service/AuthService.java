package com.example.oca42.service;


import com.example.oca42.model.AuthRequest;
import com.example.oca42.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest request) {

        Authentication authentificate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = (User) authentificate.getPrincipal();

        return AuthResponse.builder().accessToken(user.getUsername()).build();
    }
}
