package com.exapmle.java.loginbackend;

import com.exapmle.java.loginbackend.Payload.request.SignupRequest;
import com.exapmle.java.loginbackend.entities.Role;
import com.exapmle.java.loginbackend.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class LoginBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginBackendApplication.class, args);
    }

}
