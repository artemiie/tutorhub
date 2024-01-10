package com.tutorhub.web.security.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    @Email(
            message = "Username must be a valid email."
    )
    @NotNull(
            message = "Username must be not null."
    )
    @NotEmpty(
            message = "Username must be not empty."
    )
    private String username;

    @NotNull(
            message = "Password must be not null."
    )
    @NotEmpty(
            message = "Password must be not empty."
    )
    private String password;

}
