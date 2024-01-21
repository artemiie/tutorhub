package com.tutorhub.web.security.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestoreRequest {

    @NotNull(
            message = "Username must be not null."
    )
    @NotEmpty(
            message = "Username must be not empty."
    )
    @Schema(description = "User username", example = "johndoe@gmail.com")
    private String username;

}
