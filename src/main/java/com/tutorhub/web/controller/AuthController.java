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
import com.tutorhub.web.dto.mapper.UserMapper;
import com.tutorhub.web.dto.user.UserCreateDto;
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
          responseCode = Register.ResponseCode201.CODE,
          description = Register.ResponseCode201.DESCRIPTION),
      @ApiResponse(
          responseCode = Register.ResponseCode400.CODE,
          description = Register.ResponseCode400.DESCRIPTION)
  })
  @PostMapping("/register")
  public void register(
      @RequestBody @Validated final UserCreateDto userDTO) {
    User user = userMapper.fromDto(userDTO);
    authService.register(user);
  }

  @Operation(summary = Login.SUMMARY, description = Login.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Login.ResponseCode200.CODE,
          description = Login.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Login.ResponseCode401.CODE,
          description = Login.ResponseCode401.DESCRIPTION)
  })
  @PostMapping("/login")
  public AuthResponse login(@RequestBody @Validated final AuthRequest request) {
    return authService.login(request);
  }

  @Operation(summary = Restore.SUMMARY, description = Restore.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Restore.ResponseCode204.CODE,
          description = Restore.ResponseCode204.DESCRIPTION),
      @ApiResponse(
          responseCode = Restore.ResponseCode404.CODE,
          description = Restore.ResponseCode404.DESCRIPTION)
  })
  @PostMapping("/restore")
  public void restore(@RequestBody final RestoreRequest request) {
    authService.restore(request);
  }

  @Operation(summary = Reset.SUMMARY, description = Reset.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Reset.ResponseCode204.CODE,
          description = Reset.ResponseCode204.DESCRIPTION),
      @ApiResponse(
          responseCode = Reset.ResponseCode403.CODE,
          description = Reset.ResponseCode403.DESCRIPTION)
  })
  @PostMapping("/reset")
  public void reset(@RequestBody final ResetRequest request) {
    authService.reset(request);
  }

  @Operation(summary = Confirm.SUMMARY, description = Confirm.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Confirm.ResponseCode204.CODE,
          description = Confirm.ResponseCode204.DESCRIPTION),
      @ApiResponse(
          responseCode = Confirm.ResponseCode400.CODE,
          description = Confirm.ResponseCode400.DESCRIPTION)
  })
  @GetMapping("/confirm")
  public void confirm(
      @Parameter(description = "The token received to confirm the user's email")
      @RequestParam(value = "token") final String token) {
    authService.confirmUserEmail(token);
  }
}
