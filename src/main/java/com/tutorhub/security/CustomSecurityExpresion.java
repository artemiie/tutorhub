package com.tutorhub.security;

import com.tutorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpresion")
@RequiredArgsConstructor
public class CustomSecurityExpresion {
  private final UserService userService;

  public boolean isCourseOwner(final String courseId) {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
    SecurityUser user = (SecurityUser) authentication.getPrincipal();
    Long userId = user.getId();
    return userService.isCourseOwner(userId, Long.valueOf(courseId));
  }

  public boolean isCurrentUser(final String userId) {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
    SecurityUser user = (SecurityUser) authentication.getPrincipal();
    String currentUserId = String.valueOf(user.getId());
    return userId.equals(currentUserId);
  }
}
