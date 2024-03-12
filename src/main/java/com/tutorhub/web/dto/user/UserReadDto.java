package com.tutorhub.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserReadDto(
    @Schema(description = "User id", example = "1")
    Long id,
    @Schema(description = "User full name", example = "John Doe")
    String fullname,
    @Schema(
        description = "User's username(email)",
        example = "johndoe@gmail.com")
    String username
) {
}
