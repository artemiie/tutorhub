package com.tutorhub.web.controller;

import com.tutorhub.model.exception.ResourceAlreadyExistsException;
import com.tutorhub.model.exception.ResourceNotFoundException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFound() {
        return "Not found.";
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public String resourceAlreadyExists() {
        return "Already exists.";
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public String internalAuthentication() {
        return "Authentication failed.";
    }

    @ExceptionHandler
    public String exception(
            final Exception e
    ) {
        e.printStackTrace();
        return e.getMessage();
    }

}
