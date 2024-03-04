package com.tutorhub.web.controller;

import com.tutorhub.model.user.User;
import com.tutorhub.security.jwt.AuthRequest;
import com.tutorhub.security.jwt.AuthResponse;
import com.tutorhub.security.jwt.ResetRequest;
import com.tutorhub.security.jwt.RestoreRequest;
import com.tutorhub.service.AuthService;
import com.tutorhub.web.controller.swagger.constants.AuthApiConstants;
import com.tutorhub.web.controller.swagger.constants.AuthApiConstants.Confirm;
import com.tutorhub.web.controller.swagger.constants.AuthApiConstants.Login;
import com.tutorhub.web.controller.swagger.constants.AuthApiConstants.Register;
import com.tutorhub.web.controller.swagger.constants.AuthApiConstants.Reset;
import com.tutorhub.web.controller.swagger.constants.AuthApiConstants.Restore;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.UserDTO;
import com.tutorhub.web.dto.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = AuthApiConstants.NAME, description = AuthApiConstants.DESCRIPTION)
public class AuthController {

  private final AuthService authService;
  private final UserMapper userMapper;

  @Operation(summary = Register.SUMMARY, description = Register.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Register.RescponseCode201.CODE,
          description = Register.RescponseCode201.DESCRIPTION),
      @ApiResponse(
          responseCode = Register.RescponseCode400.CODE,
          description = Register.RescponseCode400.DESCRIPTION)
  })
  @PostMapping("/register")
  public void register(
      @RequestBody @Validated(OnCreate.class) final UserDTO userDTO) {
    User user = userMapper.fromDto(userDTO);
    authService.register(user);
  }

  @Operation(summary = Login.SUMMARY, description = Login.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Login.RescponseCode200.CODE,
          description = Login.RescponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Login.RescponseCode401.CODE,
          description = Login.RescponseCode401.DESCRIPTION)
  })
  @PostMapping("/login")
  public AuthResponse login(@RequestBody @Validated final AuthRequest request) {
    return authService.login(request);
  }

  @Operation(summary = Restore.SUMMARY, description = Restore.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Restore.RescponseCode204.CODE,
          description = Restore.RescponseCode204.DESCRIPTION),
      @ApiResponse(
          responseCode = Restore.RescponseCode404.CODE,
          description = Restore.RescponseCode404.DESCRIPTION)
  })
  @PostMapping("/restore")
  public void restore(@RequestBody final RestoreRequest request) {
    authService.restore(request);
  }

  @Operation(summary = Reset.SUMMARY, description = Reset.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Reset.RescponseCode204.CODE,
          description = Reset.RescponseCode204.DESCRIPTION),
      @ApiResponse(
          responseCode = Reset.RescponseCode403.CODE,
          description = Reset.RescponseCode403.DESCRIPTION)
  })
  @PostMapping("/reset")
  public void reset(@RequestBody final ResetRequest request) {
    authService.reset(request);
  }

  @Operation(summary = Confirm.SUMMARY, description = Confirm.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Confirm.RescponseCode204.CODE,
          description = Confirm.RescponseCode204.DESCRIPTION),
      @ApiResponse(
          responseCode = Confirm.RescponseCode400.CODE,
          description = Confirm.RescponseCode400.DESCRIPTION)
  })
  @GetMapping("/confirm")
  public void confirm(
      @Parameter(description = "The token received to confirm the user's email")
      @RequestParam(value = "token", required = true) final String token) {
    authService.confirmUserEmail(token);
  }
}
