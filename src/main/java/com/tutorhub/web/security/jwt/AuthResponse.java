package com.tutorhub.web.security.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    @Schema(description = "Access token",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
                      + ".eyJzdWIiOiJleCIsImlhdCI6MTUxNjIzOTAyMn0."
                      + "x7mEevSaClrow5HpX4LnMs2yPlDIIF0FReSVmiTPhhA")
    private String access;

    @Schema(description = "Refresh token",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
                      + "eyJzdWIiOiJleDIiLCJpYXQiOjE1MTYyMzkwMjJ9."
                      + "L3GHEjS_gCb6Vo7D8rLaPfXJfmgw624AApOiu-812O0")
    private String refresh;

}
