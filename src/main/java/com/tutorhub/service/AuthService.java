package com.tutorhub.service;

import com.tutorhub.model.User;
import com.tutorhub.web.security.jwt.AuthRequest;
import com.tutorhub.web.security.jwt.AuthResponse;
import com.tutorhub.web.security.jwt.ResetRequest;
import com.tutorhub.web.security.jwt.RestoreRequest;

public interface AuthService {

    void register(User user);

    AuthResponse login(AuthRequest request);

    void restore(RestoreRequest request);

    void reset(ResetRequest request);

    boolean checkToken(String token);
}
