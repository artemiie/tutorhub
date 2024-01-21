package com.tutorhub.web.controller;

import com.tutorhub.model.exception.ExceptionBody;
import com.tutorhub.model.exception.ResourceAlreadyExistsException;
import com.tutorhub.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionBody resourceNotFound(final ResourceNotFoundException exception) {
    return new ExceptionBody(exception.getMessage());
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ExceptionBody resourceAlreadyExists(final ResourceAlreadyExistsException exception) {
    return new ExceptionBody(exception.getMessage());
  }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ExceptionBody internalAuthentication(final InternalAuthenticationServiceException exception) {
      return new ExceptionBody("Authentication failed.");
    }

    @ExceptionHandler
    public String exception(
            final Exception e
    ) {
        e.printStackTrace();
        return e.getMessage();
    }

}
