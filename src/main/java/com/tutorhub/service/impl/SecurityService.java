package com.tutorhub.service.impl;

import com.tutorhub.model.User;
import com.tutorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
  private final UserService userService;

  public User getCurrentLoggedUser() {
    Authentication currentLoggedUser = SecurityContextHolder.getContext().getAuthentication();
    return userService.getByUsername(currentLoggedUser.getName());
  }
}
