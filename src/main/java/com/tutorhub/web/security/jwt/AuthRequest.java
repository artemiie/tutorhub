package com.tutorhub.web.security.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

  @Schema(description = "User full name", example = "john Doe")
  @Email(message = "Username must be a valid email.")
  @NotNull(message = "Username must be not null.")
  @NotEmpty(message = "Username must be not empty.")
  private String username;

  @Schema(
      description = "User crypted password",
      example = "$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m")
  @NotNull(message = "Password must be not null.")
  @NotEmpty(message = "Password must be not empty.")
  private String password;
}
