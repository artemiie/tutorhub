package com.tutorhub.web.security.jwt;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestoreRequest {

    @NotNull(
            message = "Token must be not null."
    )
    @NotEmpty(
            message = "Token must be not empty."
    )
    private String token;

    @NotNull(
            message = "Password must be not null."
    )
    @NotEmpty(
            message = "Password must be not empty."
    )
    private String password;

}
