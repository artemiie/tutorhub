package com.tutorhub.security;

import com.tutorhub.model.user.User;
import com.tutorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SecurityUserService implements UserDetailsService {

  private final UserService userService;

  @Override
  public SecurityUser loadUserByUsername(final String username) {
    User user = userService.getByUsername(username);
    return new SecurityUser(user);
  }
}
