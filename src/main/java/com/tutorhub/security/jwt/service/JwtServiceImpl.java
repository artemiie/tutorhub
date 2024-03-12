package com.tutorhub.security.jwt.service;

import com.tutorhub.security.jwt.TokenType;
import com.tutorhub.security.jwt.exception.InvalidTokenException;
import com.tutorhub.security.jwt.service.params.JwtProperties;
import com.tutorhub.security.jwt.service.params.TokenParameters;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

  private final JwtProperties properties;
  private final Map<TokenType, Duration> tokenDurationByType = new HashMap<>();
  private SecretKey key;

  @PostConstruct
  protected void init() {
    key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
    tokenDurationByType.put(TokenType.ACTIVATION, properties.getActivation());
    tokenDurationByType.put(TokenType.ACCESS, properties.getAccess());
    tokenDurationByType.put(TokenType.RESTORE, properties.getRestore());
    tokenDurationByType.put(TokenType.REFRESH, properties.getRefresh());
  }

  @Override
  public String generate(final String username, final TokenType type) {
    TokenParameters params =
        TokenParameters
            .builder(username, tokenDurationByType.get(type))
            .type(type)
            .build();

    Claims claims = Jwts.claims()
        .subject(params.getSubject())
        .add(params.getFields()).build();

    return Jwts.builder()
        .claims(claims)
        .issuedAt(params.getIssuedAt())
        .expiration(params.getExpiredAt())
        .signWith(key)
        .compact();
  }

  @Override
  public boolean isValid(final String token, final TokenType tokenType) {
    try {
      Jws<Claims> claims =
          Jwts.parser()
              .verifyWith(key)
              .build()
              .parseSignedClaims(token);

      TokenType type =
          TokenType.valueOf((String) claims.getPayload().get("type"));

      return claims
          .getPayload()
          .getExpiration()
          .after(new Date()) && tokenType.equals(type);
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  @Override
  public void check(final String token, final TokenType type) {
    if (!isValid(token, TokenType.ACTIVATION)) {
      throw new InvalidTokenException();
    }
  }

  @Override
  public HashMap<String, Object> fields(final String token) {
    Jws<Claims> claims =
        Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
    return new HashMap<>(claims.getPayload());
  }

  @Override
  public Object fieldBy(final String token, final String by) {
    Map<String, Object> fields = fields(token);
    return fields.get(by);
  }
}
