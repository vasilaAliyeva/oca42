package com.example.oca42.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String accessToken;
    private String refreshToken;

}
