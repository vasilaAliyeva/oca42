package com.example.oca42.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                .formLogin(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/auth**").permitAll()
                                .anyRequest().authenticated()
                );


        return http.build();
    }
}
