package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceAlreadyExistsException;
import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.mail.MailService;
import com.tutorhub.model.user.User;
import com.tutorhub.security.jwt.AuthRequest;
import com.tutorhub.security.jwt.ResetRequest;
import com.tutorhub.security.jwt.RestoreRequest;
import com.tutorhub.security.jwt.TokenType;
import com.tutorhub.security.jwt.exception.InvalidTokenException;
import com.tutorhub.security.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

import static com.tutorhub.model.user.Role.ROLE_USER;
import static com.tutorhub.testfactory.UserTestFactory.getUserTest;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

  private final Long userId = 1L;
  private final String subject = "subject";
  private final String username = "username";
  private final String password = "password";
  private final String resetToken = "resetToken";
  private final String accessToken = "accessToken";
  private final String refreshToken = "refreshToken";
  private final String encodedPassword = "encodedPassword";

  @Mock
  private JwtService jwtService;
  @Mock
  private MailService mailService;
  @Mock
  private UserServiceImpl userService;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private AuthenticationManager authenticationManager;

  @InjectMocks
  private AuthServiceImpl authService;

  @Test
  void login() {
    var user = getUserTest(userId);

    doReturn(user)
        .when(userService)
        .findByUsername(any());
    doReturn(accessToken)
        .when(jwtService)
        .generate(username, TokenType.ACCESS);
    doReturn(refreshToken)
        .when(jwtService)
        .generate(username, TokenType.RESTORE);

    var authRequest = new AuthRequest();
    authRequest.setUsername(username);
    authRequest.setPassword(password);

    var authResponse = authService.login(authRequest);

    assertAll(
        () -> assertEquals(userId, authResponse.getUserId()),
        () -> assertEquals(accessToken, authResponse.getAccess()),
        () -> assertEquals(refreshToken, authResponse.getRefresh())
    );

    verify(authenticationManager).authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequest.getUsername(), authRequest.getPassword()));
    verify(mailService).send(any());
  }

  @Test
  void register() {
    var user = getUserTest(userId);

    doReturn(false)
        .when(userService)
        .existsByUsername(user.getUsername());

    doReturn(encodedPassword).when(passwordEncoder).encode(any());

    authService.register(user);

    final ArgumentCaptor<User> captor =
        ArgumentCaptor.forClass(User.class);

    verify(userService).create(captor.capture());

    final User savedUser = captor.getValue();

    assertAll(
        () -> assertNotNull(savedUser),
        () -> assertNotNull(savedUser.getRole()),
        () -> assertTrue(savedUser.getRole().stream().findFirst().isPresent()),
        () -> assertTrue(savedUser.getRole().contains(ROLE_USER)),
        () -> assertEquals(encodedPassword, savedUser.getPassword())
    );

    verify(mailService).send(any());
    verify(jwtService).generate(user.getUsername(), TokenType.ACTIVATION);
  }

  @Test
  void register_alreadyExistingUser() {
    doReturn(true).when(userService).existsByUsername(any());

    assertThrows(
        ResourceAlreadyExistsException.class,
        () -> authService.register(getUserTest(1L)));
  }

  @Test
  void restore() {
    var user = getUserTest(userId);
    doReturn(user).when(userService).findByUsername(user.getUsername());

    var restoreRequest = new RestoreRequest(user.getUsername());

    authService.restore(restoreRequest);

    verify(jwtService).generate(user.getUsername(), TokenType.RESTORE);
    verify(mailService).send(any());
  }

  @Test
  void restore_userDontExists() {
    doReturn(null).when(userService).findByUsername(any());

    var restoreRequest = new RestoreRequest(null);

    assertThrows(
        ResourceNotFoundException.class,
        () -> authService.restore(restoreRequest));

    verify(userService).findByUsername(restoreRequest.username());
  }

  @Test
  void reset() {
    var resetRequest = new ResetRequest();
    resetRequest.setToken(resetToken);
    resetRequest.setPassword(password);

    doReturn(true)
        .when(jwtService)
        .isValid(resetRequest.getToken(), TokenType.RESTORE);

    var fields = HashMap.newHashMap(1);
    fields.put(subject, username);

    doReturn(fields).when(jwtService).fields(resetRequest.getToken());

    var user = getUserTest(1L);
    doReturn(user).when(userService).findByUsername(username);

    authService.reset(resetRequest);

    verify(passwordEncoder).encode(password);
  }

  @Test
  void reset_notValidToken() {
    var resetRequest = new ResetRequest();
    resetRequest.setToken(resetToken);

    doReturn(false)
        .when(jwtService)
        .isValid(resetRequest.getToken(), TokenType.RESTORE);

    assertThrows(
        InvalidTokenException.class,
        () -> authService.reset(resetRequest));
  }

  @Test
  void confirmUserEmail() {
    String activationToken = "activationToken";

    doNothing().when(jwtService).check(activationToken, TokenType.ACTIVATION);
    doReturn(username).when(jwtService).fieldBy(activationToken, subject);
    doNothing().when(userService).enable(username);

    authService.confirmUserEmail(activationToken);
  }
}
