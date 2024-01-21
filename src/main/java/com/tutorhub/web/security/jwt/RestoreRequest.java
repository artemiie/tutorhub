package com.tutorhub.web.security.jwt;

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
    private String username;

}
