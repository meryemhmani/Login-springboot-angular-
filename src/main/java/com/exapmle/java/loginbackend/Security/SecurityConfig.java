package com.exapmle.java.loginbackend.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public Long userId() {
        return 123L; // Remplacez par la valeur appropriée
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // Assurez-vous de configurer un AuthenticationManager personnalisé si nécessaire
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated();
        return http.build();
    }

}
