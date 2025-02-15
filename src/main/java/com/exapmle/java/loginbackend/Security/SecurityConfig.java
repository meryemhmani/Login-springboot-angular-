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
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // Crée un AuthenticationManager basé sur la configuration de sécurité fournie par Spring Security.
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utilisation de BCrypt pour encoder les mots de passe.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Désactivation de la protection CSRF, car elle n'est pas nécessaire pour les API REST.
                .authorizeRequests()
                .requestMatchers("/login", "/register").permitAll() // Autorisation d'accès public pour les routes de connexion et d'inscription.
                .anyRequest().authenticated();  // Toute autre requête doit être authentifiée.
        return http.build();
    }
}
