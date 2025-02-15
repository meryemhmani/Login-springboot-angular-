package com.exapmle.java.loginbackend.controllers;

import com.exapmle.java.loginbackend.Payload.request.LoginRequest;
import com.exapmle.java.loginbackend.Payload.request.SignupRequest;
import com.exapmle.java.loginbackend.Payload.response.MEssageResponse;
import com.exapmle.java.loginbackend.Payload.response.UserInfoResponse;
import com.exapmle.java.loginbackend.Repository.RoleRepository;
import com.exapmle.java.loginbackend.Repository.UserRepository;
import com.exapmle.java.loginbackend.Security.jwtUtil;
import com.exapmle.java.loginbackend.entities.ERole;
import com.exapmle.java.loginbackend.entities.Role;
import com.exapmle.java.loginbackend.entities.User;
import com.exapmle.java.loginbackend.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    jwtUtil jwtUtils;

    // Endpoint pour la connexion de l'utilisateur
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Authentification de l'utilisateur avec ses identifiants
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Récupérer les détails de l'utilisateur authentifié
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Générer un cookie contenant le token JWT
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        // Extraire les rôles de l'utilisateur
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Retourner la réponse avec les détails de l'utilisateur et le token JWT
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    // Endpoint pour l'inscription de l'utilisateur
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // Vérification si le nom d'utilisateur existe déjà
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MEssageResponse("Error: Username is already taken!"));
        }

        // Vérification si l'email existe déjà
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MEssageResponse("Error: Email is already in use!"));
        }

        // Création d'un nouvel utilisateur
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        // Définir les rôles de l'utilisateur
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        // Assigner les rôles à l'utilisateur
        user.setRoles(roles);
        userRepository.save(user);

        // Retourner une réponse indiquant que l'inscription a réussi
        return ResponseEntity.ok(new MEssageResponse("User registered successfully!"));
    }

    // Endpoint pour la déconnexion de l'utilisateur
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        // Nettoyer le cookie JWT
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MEssageResponse("You've been signed out!"));
    }
}
