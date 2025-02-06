package com.exapmle.java.loginbackend.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static javax.crypto.Cipher.SECRET_KEY;

public class JwtUtil {


    @Value("${jwt.secret}")
    public static final String SECRET = "W8BQhSawPRwhBeU3gxUZhPbPDFIpPf9UXThM2yxCbY77gVA5210SPvxNwZoP+YeaUq7RszSvCjFl+dRUIT/yX5ZueJ2XkJerKYHJO4djlL0=";
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    @Value("${jwt.access-token-expiration}")

    public static final long EXPIRE_ACCESS_TOKEN = 24 * 60 * 1000;
    @Value("${jwt.refresh-token-expiration}")
    public static final long EXPIRE_REFRESH_TOKEN = 15 * 60 * 1000;


    public static String generateToken(UserDetails userDetails, Map<String, Object> extractclaims) {
        return Jwts.builder()
                .setClaims(extractclaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN))
                .signWith(SignatureAlgorithm.HS512, getSignInKey())
                .compact();
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public String generateAccessToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN))
                .withClaim("roles", userDetails.getAuthorities().stream()
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_REFRESH_TOKEN))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSignInKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract claims from JWT", e);
        }
    }

    private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(String.valueOf(SECRET_KEY));
        return Keys.hmacShaKeyFor(keyBytes);
    }
}