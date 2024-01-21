package com.tutorhub.web.controller;

import com.tutorhub.model.exception.ExceptionBody;
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
  public ExceptionBody resourceNotFound(final ResourceNotFoundException exception) {
    return new ExceptionBody(exception.getMessage());
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ExceptionBody resourceAlreadyExists(final ResourceAlreadyExistsException exception) {
    return new ExceptionBody(exception.getMessage());
  }

    @ExceptionHandler({AuthenticationException.class, InternalAuthenticationServiceException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionBody internalAuthentication() {
      return new ExceptionBody("Authentication failed.");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({org.springframework.security.access.AccessDeniedException.class})
    public String handleAccessDenied() {
        return "Forbidden.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidTokenException.class})
    public String handleInvalidToken() {
        return "Bad request params.";
    }

    @ExceptionHandler
    public String exception(
            final Exception e
    ) {
        e.printStackTrace();
        return e.getMessage();
    }

}
