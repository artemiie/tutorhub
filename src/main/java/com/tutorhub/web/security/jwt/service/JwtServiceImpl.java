package com.tutorhub.web.security.jwt.service;

import com.tutorhub.web.security.jwt.TokenType;
import com.tutorhub.web.security.jwt.service.params.JwtProperties;
import com.tutorhub.web.security.jwt.service.params.TokenParameters;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties properties;
    private SecretKey key;

    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
    }

    @Override
    public String generate(
            final TokenParameters params
    ) {
        Claims claims = Jwts.claims()
                .subject(params.getSubject())
                .add(params.getFields())
                .build();
        return Jwts.builder()
                .claims(claims)
                .issuedAt(params.getIssuedAt())
                .expiration(params.getExpiredAt())
                .signWith(key)
                .compact();
    }

    @Override
    public boolean isValid(
            final String token,
            final TokenType tokenType
    ) {
        try {
            Jws<Claims> claims = Jwts
                    .parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            TokenType type = TokenType.valueOf(
                    (String) claims.getPayload()
                            .get("type")
            );
            return claims.getPayload()
                    .getExpiration()
                    .after(new Date())
                    && tokenType.equals(type);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public HashMap<String, Object> fields(
            final String token
    ) {
        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        return new HashMap<>(claims.getPayload());
    }

}
