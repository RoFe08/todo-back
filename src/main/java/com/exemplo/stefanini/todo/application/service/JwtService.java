package com.exemplo.stefanini.todo.application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration-ms:86400000}")
    private long expirationMs;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String subject, String email, String name) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .subject(subject)
                .claim("email", email)
                .claim("name", name)
                .issuedAt(now)
                .expiration(exp)
                .signWith(key())
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValid(String token) {
        try {
            Claims c = parseClaims(token);
            Date exp = c.getExpiration();
            return exp != null && exp.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String subject(String token) {
        return parseClaims(token).getSubject();
    }

    public String email(String token) {
        Object v = parseClaims(token).get("email");
        return v == null ? null : v.toString();
    }
}
