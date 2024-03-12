package com.tutorhub.service;

import com.tutorhub.model.user.User;
import com.tutorhub.security.jwt.AuthRequest;
import com.tutorhub.security.jwt.AuthResponse;
import com.tutorhub.security.jwt.ResetRequest;
import com.tutorhub.security.jwt.RestoreRequest;

public interface AuthService {

  void register(User user);

  AuthResponse login(AuthRequest request);

  void restore(RestoreRequest request);

  void reset(ResetRequest request);

  void confirmUserEmail(String token);
}
