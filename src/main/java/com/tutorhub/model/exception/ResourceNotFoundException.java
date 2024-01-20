package com.tutorhub.model.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException() {

  }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
