package com.exapmle.java.loginbackend;

import com.exapmle.java.loginbackend.entities.Role;
import com.exapmle.java.loginbackend.entities.User;
import com.exapmle.java.loginbackend.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoginBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginBackendApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {
            String email = "meryemhmani02@gmail.com";
            String firstname = "mimi";
            String lastname = "mimita";
            String username = "mimicha";
            String password = "mimi123";
            
            if (!userService.existsByEmail(email)) {
                try {
                    User user = new User();
                    user.setRole(Role.ADMIN);
                    user.setEmail(email);
                    user.setFirstName(firstname);
                    user.setLastName(lastname);
                    user.setUsername(username);
                    user.setPassword(passwordEncoder.encode(password));

                    userService.addNewUser(user);

                    System.out.println("L'utilisateur avec l'e-mail " + email + " a été ajouté.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
                }
            } else {
                System.out.println("L'utilisateur avec l'e-mail " + email + " existe déjà.");
            }
        };
    }
}
