package com.tutorhub.service;

import com.tutorhub.model.user.User;

public interface UserService extends CrudService<User> {

  User getByUsername(String username);

  boolean existsByUsername(String username);

  void enable(String username);

  boolean isCourseOwner(Long userId, Long noteId);
}
