package com.tutorhub.model.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException() {

  }

  public ResourceNotFoundException(final String message) {
    super(message);
  }
}
