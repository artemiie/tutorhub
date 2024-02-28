package com.tutorhub.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
  public ResourceAlreadyExistsException() {
  }

  public ResourceAlreadyExistsException(final String message) {
    super(message);
  }
}
