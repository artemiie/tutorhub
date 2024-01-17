package com.tutorhub.web.security.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestoreRequest {

    @Schema(
            description = "Restore token",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
                      + "eyJzdWIiOiJleDMiLCJpYXQiOjE1MTYyMzkwMjJ9."
                      + "zF8AzhvONcMUiy_JnC-tFrxzYJixuIApxfqOzLrHlBc"
    )
    @NotNull(message = "Token must be not null.")
    @NotEmpty(message = "Token must be not empty.")
    private String token;

    @Schema(
            description = "New User password",
            example = "123456"
    )
    @NotNull(message = "Password must be not null.")
    @NotEmpty(message = "Password must be not empty.")
    private String password;

}
