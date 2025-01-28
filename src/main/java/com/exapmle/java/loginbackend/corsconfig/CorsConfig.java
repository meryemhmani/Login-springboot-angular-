package corsconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // Permet l'utilisation de cookies ou d'identifiants d'authentification
        config.setAllowedOrigins(Arrays.asList("http://example.com", "http://anotherdomain.com")); // Domaines autorisés
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // En-têtes autorisés
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Méthodes HTTP autorisées
        config.setMaxAge(3600L); // Durée de cache (1 heure)

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
