package com.tutorhub.service.impl;

import static com.tutorhub.model.MailType.REGISTRATION;

import com.tutorhub.model.MailType;
import com.tutorhub.model.User;
import com.tutorhub.model.exception.ResourceAlreadyExistsException;
import com.tutorhub.service.AuthService;
import com.tutorhub.service.MailService;
import com.tutorhub.service.UserService;
import com.tutorhub.web.security.jwt.AuthRequest;
import com.tutorhub.web.security.jwt.AuthResponse;
import com.tutorhub.web.security.jwt.RestoreRequest;
import com.tutorhub.web.security.jwt.TokenType;
import com.tutorhub.web.security.jwt.exception.InvalidTokenException;
import com.tutorhub.web.security.jwt.service.JwtService;
import com.tutorhub.web.security.jwt.service.params.JwtProperties;
import com.tutorhub.web.security.jwt.service.params.TokenParameters;
import java.util.Map;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;

    @Override
    public void register(
            final User user
    ) {
        if (userService.existsByUsername(user.getUsername())) {
            throw new ResourceAlreadyExistsException();
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        userService.create(user);
        String token = jwtService.generate(
                TokenParameters.builder(
                                user.getUsername(),
                                jwtProperties.getActivation()
                        )
                        .type(TokenType.ACTIVATION)
                        .build()
        );

    mailService.sendEmail(
        user.getUsername(),
        user.getFullName(),
        REGISTRATION,
        new Properties() {
          {
            put("token", token);
          }
        });
  }

    @Override
    public AuthResponse login(
            final AuthRequest request
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        AuthResponse response = new AuthResponse();
        response.setAccess(
                jwtService.generate(
                        TokenParameters.builder(
                                        request.getUsername(),
                                        jwtProperties.getAccess()
                                )
                                .type(TokenType.ACCESS)
                                .build()
                )
        );
        response.setRefresh(
                jwtService.generate(
                        TokenParameters.builder(
                                        request.getUsername(),
                                        jwtProperties.getRefresh()
                                )
                                .type(TokenType.REFRESH)
                                .build()
                )
        );

        User userOnDb = userService.getByUsername(request.getUsername());

        mailService.sendEmail(
                request.getUsername(), userOnDb.getFullName(), MailType.LOGIN, new Properties());

        return response;
    }

    @Override
    public void restore(
            final String username
    ) {
        if (userService.existsByUsername(username)) {
            String token = jwtService.generate(
                    TokenParameters.builder(
                                    username,
                                    jwtProperties.getRestore()
                            )
                            .type(TokenType.RESTORE)
                            .build()
            );
            //TODO send email with token
        }
    }

    @Override
    public void reset(
            final RestoreRequest request
    ) {
        if (!jwtService.isValid(request.getToken(), TokenType.RESTORE)) {
            throw new InvalidTokenException();
        }
        Map<String, Object> fields = jwtService.fields(request.getToken());
        User user = userService.getByUsername(
                (String) fields.get("subject")
        );
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        userService.update(user);
    }

    @Override
    public boolean checkToken(final String token) {
        if (!jwtService.isValid(token, TokenType.ACTIVATION)) {
            throw new InvalidTokenException();
        }
        return true;
    }

}
