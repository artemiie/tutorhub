package com.tutorhub.web.controller;

import com.tutorhub.model.User;
import com.tutorhub.service.AuthService;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.StudentDTO;
import com.tutorhub.web.dto.TutorDTO;
import com.tutorhub.web.dto.mapper.UserMapper;
import com.tutorhub.web.security.jwt.AuthRequest;
import com.tutorhub.web.security.jwt.AuthResponse;
import com.tutorhub.web.security.jwt.ResetRequest;
import com.tutorhub.web.security.jwt.RestoreRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "${api.auth.name}", description = "${api.auth.description}")
public class AuthController {

  private final UserMapper userMapper;

  private final AuthService authService;

  @PostMapping("/register/tutor")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(
      summary = "${api.auth.registration.tutor-description}",
      description = "${api.auth.registration.notes}")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "${api.responseCodes.create.description}"),
    @ApiResponse(
        responseCode = "422",
        description = "${api.responseCodes.unprocessableEntity.description}",
        content = {@Content(schema = @Schema(implementation = Void.class))})
  })
  public void registerTutor(@RequestBody @Validated(OnCreate.class) final TutorDTO tutorDTO) {
    User tutor = userMapper.fromDto(tutorDTO);
    authService.register(tutor);
  }

  @Operation(
      summary = "${api.auth.registration.student-description}",
      description = "${api.auth.registration.notes}")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "${api.responseCodes.create.description}"),
    @ApiResponse(
        responseCode = "422",
        description = "${api.responseCodes.unprocessableEntity.description}",
        content = {@Content(schema = @Schema(implementation = Void.class))})
  })
  @PostMapping("/register/student")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerStudent(@RequestBody @Validated(OnCreate.class) final StudentDTO studentDTO) {
    User student = userMapper.fromDto(studentDTO);
    authService.register(student);
  }

  @Operation(summary = "${api.auth.login.description}", description = "${api.auth.login.notes}")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    @ApiResponse(
        responseCode = "401",
        description = "${api.responseCodes.badCredentials.description}",
        content = {@Content(schema = @Schema(implementation = Void.class))})
  })
  @PostMapping("/login")
  public AuthResponse login(@RequestBody @Validated final AuthRequest request) {
    return authService.login(request);
  }

  @Operation(summary = "${api.auth.restore.description}", description = "${api.auth.restore.notes}")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    @ApiResponse(
        responseCode = "404",
        description = "${api.responseCodes.notFound.description}",
        content = {@Content(schema = @Schema(implementation = Void.class))})
  })
  @PostMapping("/restore")
  public void restore(@RequestBody final RestoreRequest request) {
    authService.restore(request);
  }

  @Operation(summary = "${api.auth.reset.description}", description = "${api.auth.reset.notes}")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    @ApiResponse(
        responseCode = "403",
        description = "${api.responseCodes.badRequest.description}",
        content = {@Content(schema = @Schema(implementation = Void.class))})
  })
  @PostMapping("/reset")
  public void reset(@RequestBody final ResetRequest request) {
    authService.reset(request);
  }

  @Operation(summary = "${api.auth.confirm.description}", description = "${api.auth.confirm.notes}")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
    @ApiResponse(
        responseCode = "400",
        description = "${api.responseCodes.badRequest.description}",
        content = {@Content(schema = @Schema(implementation = Void.class))})
  })
  @GetMapping("/confirm")
  public void confirm(@RequestParam("token") final String token) {
    authService.confirmUserEmail(token);
  }
}
