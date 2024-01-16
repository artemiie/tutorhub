package com.tutorhub.service;

import com.tutorhub.model.User;
import com.tutorhub.web.security.jwt.AuthRequest;
import com.tutorhub.web.security.jwt.AuthResponse;
import com.tutorhub.web.security.jwt.RestoreRequest;

public interface AuthService {

    void register(User user);

    AuthResponse login(AuthRequest request);

    void restore(String username);

    void reset(RestoreRequest request);

    boolean checkToken(String token);
}
