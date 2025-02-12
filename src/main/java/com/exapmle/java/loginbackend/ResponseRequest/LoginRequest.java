package com.exapmle.java.loginbackend.ResponseRequest;

public class LoginRequest {

        private String email;       // Email de l'utilisateur
        private String password;    // Mot de passe de l'utilisateur

         public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

}
