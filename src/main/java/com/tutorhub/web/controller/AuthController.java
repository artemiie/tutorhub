package com.tutorhub.web.controller;

import com.tutorhub.model.User;
import com.tutorhub.service.AuthService;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.StudentDTO;
import com.tutorhub.web.dto.TutorDTO;
import com.tutorhub.web.dto.mapper.UserMapper;
import com.tutorhub.web.security.jwt.AuthRequest;
import com.tutorhub.web.security.jwt.AuthResponse;
import com.tutorhub.web.security.jwt.RestoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserMapper userMapper;

    private final AuthService authService;

    @PostMapping("/register/tutor")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTutor(
            @RequestBody @Validated(OnCreate.class) final TutorDTO tutorDTO
    ) {
        User user = userMapper.fromDto(tutorDTO);
        authService.register(user);
    }

    @PostMapping("/register/student")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerStudent(
            @RequestBody @Validated(OnCreate.class) final StudentDTO studentDTO
    ) {
        User user = userMapper.fromDto(studentDTO);
        authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody @Validated final AuthRequest request
    ) {
        return authService.login(request);
    }

    @PostMapping("/restore")
    public void restore(
            @RequestBody final String username
    ) {
        authService.restore(username);
    }

    @PostMapping("/reset")
    public void reset(
            @RequestBody final RestoreRequest request
    ) {
        authService.reset(request);
    }

    @GetMapping("/confirm")
    public void confirm(@RequestParam("token") String token){
        authService.checkToken(token);
    }

}
