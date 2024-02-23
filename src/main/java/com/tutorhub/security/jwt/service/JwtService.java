package com.tutorhub.security.jwt.service;

import com.tutorhub.security.jwt.TokenType;
import java.util.HashMap;

public interface JwtService {

  String generate(String username, TokenType type);

  boolean isValid(String token, TokenType type);

  void check(String token, TokenType type);

  HashMap<String, Object> fields(String token);

  Object fieldBy(String token, String by);
}
