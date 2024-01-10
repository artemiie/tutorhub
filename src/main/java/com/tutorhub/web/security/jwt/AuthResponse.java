package com.tutorhub.web.security.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String access;
    private String refresh;

}
