package com.tutorhub.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
    @Schema(description = "User id", example = "1")
    @NotNull(message = "Id must be not null.")
    Long id,
    @Schema(description = "User full name", example = "John Doe")
    @NotNull(message = "Full name must be not null.")
    @Size(max = 100, message = "Full name length must be less than {max}.")
    String fullname
) {
}
