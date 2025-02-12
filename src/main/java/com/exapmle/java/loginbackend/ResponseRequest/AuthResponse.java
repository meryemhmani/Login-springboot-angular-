package com.exapmle.java.loginbackend.ResponseRequest;

public class AuthResponse {
    private String token;       // Token d'authentification
    private String message;     // Message de statut (succès, erreur, etc.)
    private String username;    // Nom d'utilisateur (optionnel)
    private String role;        // Rôle de l'utilisateur (optionnel)

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
