package com.tutorhub.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateDto(

    @Schema(description = "User full name", example = "John Doe")
    @NotNull(message = "Full name must be not null.")
    @Size(max = 100, message = "Full name length must be less than {max}.")
    String fullname,

    @Schema(
        description = "User's username(email)",
        example = "johndoe@gmail.com")
    @Email(message = "Username must be a valid email.")
    @NotNull(message = "Username must be not null.")
    @Size(max = 100, message = "Username length must be less than {max}.")
    String username,

    @Schema(description = "User password", example = "12345678")
    @NotNull(message = "Password must be not null.")
    String password
) {
}
