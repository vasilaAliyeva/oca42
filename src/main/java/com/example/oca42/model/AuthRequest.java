package com.example.oca42.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthRequest {
    String username;
    String password;
}
