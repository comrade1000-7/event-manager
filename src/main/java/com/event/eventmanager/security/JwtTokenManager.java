package com.event.eventmanager.security;

import com.event.eventmanager.users.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenManager {

    private final Long tokenLifetime;
    private final Key signKey;

    public JwtTokenManager(
            @Value("${jwt.lifetime}") Long tokenLifetime,
            @Value("${jwt.sign-key}") String signKey) {
        this.tokenLifetime = tokenLifetime;
        this.signKey = new SecretKeySpec(
                signKey.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.role().name());
        Date issuedTime = new Date();
        Date expirationTime = new Date(issuedTime.getTime() + tokenLifetime);
        return Jwts.builder()
                .claims(claims)
                .subject(user.login())
                .issuedAt(issuedTime)
                .expiration(expirationTime)
                .signWith(signKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String jwtToken) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) signKey)
                    .build()
                    .parse(jwtToken);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getLoginFromToken(String jwtToken) {
        return Jwts.parser()
                .verifyWith((SecretKey) signKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .getSubject();
    }

    public String getRoleFromToken(String jwtToken) {
        return Jwts.parser()
                .verifyWith((SecretKey) signKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .get("role", String.class);
    }
}
