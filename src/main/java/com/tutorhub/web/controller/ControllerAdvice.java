package com.tutorhub.web.controller;

import com.tutorhub.model.exception.ResourceAlreadyExistsException;
import com.tutorhub.model.exception.ResourceNotFoundException;
import com.tutorhub.web.security.jwt.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resourceNotFound() {
        return "Not found.";
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String resourceAlreadyExists() {
        return "Already exists.";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class, InternalAuthenticationServiceException.class})
    public String internalAuthentication() {
        return "Authentication failed.";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({InvalidTokenException.class, org.springframework.security.access.AccessDeniedException.class})
    public String handleAccessDenied() {
        return "Forbidden.";
    }

    @ExceptionHandler
    public String exception(
            final Exception e
    ) {
        e.printStackTrace();
        return e.getMessage();
    }

}
