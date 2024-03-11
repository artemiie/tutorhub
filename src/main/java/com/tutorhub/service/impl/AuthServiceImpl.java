package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceAlreadyExistsException;
import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.mail.MailService;
import com.tutorhub.mail.model.MailInfoActivation;
import com.tutorhub.mail.model.MailInfoLogin;
import com.tutorhub.mail.model.MailInfoRestore;
import com.tutorhub.model.user.Role;
import com.tutorhub.model.user.User;
import com.tutorhub.security.jwt.AuthRequest;
import com.tutorhub.security.jwt.AuthResponse;
import com.tutorhub.security.jwt.ResetRequest;
import com.tutorhub.security.jwt.RestoreRequest;
import com.tutorhub.security.jwt.TokenType;
import com.tutorhub.security.jwt.exception.InvalidTokenException;
import com.tutorhub.security.jwt.service.JwtService;
import com.tutorhub.service.AuthService;
import com.tutorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final MailService mailService;

  @Override
  public void register(final User user) {
    if (userService.existsByUsername(user.getUsername())) {
      throw new ResourceAlreadyExistsException(
          "User with username [" + user.getUsername() + "] already exists.");
    }

    user.getRole().add(Role.ROLE_USER);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.create(user);

    String activationToken =
        jwtService.generate(user.getUsername(), TokenType.ACTIVATION);

    mailService.send(
        new MailInfoActivation(
            user.getFullname(),
            user.getUsername(),
            activationToken));
  }

  @Override
  public AuthResponse login(final AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(), request.getPassword()));
    User userOnDb = userService.findByUsername(request.getUsername());

    String accessToken =
        jwtService.generate(request.getUsername(), TokenType.ACCESS);
    String refreshToken =
        jwtService.generate(request.getUsername(), TokenType.REFRESH);

    AuthResponse response = new AuthResponse();
    response.setAccess(accessToken);
    response.setRefresh(refreshToken);
    response.setUserId(userOnDb.getId());

    mailService.send(
        new MailInfoLogin(userOnDb.getFullname(), userOnDb.getUsername()));

    return response;
  }

  @Override
  public void restore(final RestoreRequest request) {
    User user = userService.findByUsername(request.username());
    if (user == null) {
      throw new ResourceNotFoundException();
    }

    String restoreToken =
        jwtService.generate(user.getUsername(), TokenType.RESTORE);

    mailService.send(
        new MailInfoRestore(
            user.getFullname(), request.username(), restoreToken));
  }

  @Override
  public void reset(final ResetRequest request) {
    if (!jwtService.isValid(request.getToken(), TokenType.RESTORE)) {
      throw new InvalidTokenException();
    }

    String username =
        (String) jwtService.fieldBy(request.getToken(), "subject");

    boolean userExists = userService.existsByUsername(username);
    if (!userExists) {
      throw new ResourceNotFoundException(
          "User with username [%S] already exists".formatted(username));
    }
    String newPassword = passwordEncoder.encode(request.getPassword());

    userService.resetPassword(newPassword, username);
  }

  @Override
  public void confirmUserEmail(final String token) {
    jwtService.check(token, TokenType.ACTIVATION);
    String username = (String) jwtService.fieldBy(token, "subject");
    userService.enable(username);
  }
}
