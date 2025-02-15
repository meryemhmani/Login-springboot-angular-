package com.exapmle.java.loginbackend.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;

@Component
public class jwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(jwtUtil.class);

    @Value("${example.app.jwtSecret}")
    private String jwtSecret;

    @Value("${example.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${example.app.jwtCookieName}")
    private String jwtCookie;

    /**
     * Extracts JWT from cookies in the HTTP request.
     *
     * @param request HTTP request.
     * @return JWT token from cookies, or null if not found.
     */
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * Generates JWT token and returns it as a cookie.
     *
     * @param userPrincipal UserDetails of the user.
     * @return ResponseCookie containing the JWT token.
     */
    public ResponseCookie generateJwtCookie(UserDetails userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        return ResponseCookie.from(jwtCookie, jwt)
                .path("/api")
                .maxAge(24 * 60 * 60) // 1 day
                .httpOnly(true)
                .secure(true) // Ensure cookie is sent over HTTPS
                .sameSite("None") // Enable cross-site usage (important for CORS)
                .build();
    }

    /**
     * Returns a clean JWT cookie (used for logout).
     *
     * @return ResponseCookie with null value to remove the cookie.
     */
    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(jwtCookie, null)
                .path("/api")
                .maxAge(0) // Expire the cookie immediately
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
    }

    /**
     * Extracts the username from the JWT token.
     *
     * @param token JWT token.
     * @return Username extracted from the token.
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Returns the secret key used to sign JWT tokens.
     *
     * @return Secret key for JWT signing.
     */
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /**
     * Validates the JWT token.
     *
     * @param authToken JWT token.
     * @return true if token is valid, false otherwise.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Generates a JWT token from the username.
     *
     * @param username Username to include in the JWT token.
     * @return JWT token as a string.
     */
    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
}
