package com.tutorhub.security.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RestoreRequest(
    @NotNull(message = "Username must be not null.")
    @NotEmpty(message = "Username must be not empty.")
    @Email
    @Schema(description = "User username", example = "johndoe@gmail.com")
    String username) {
}
