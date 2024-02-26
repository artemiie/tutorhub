package com.tutorhub.testfactory;

import com.tutorhub.model.user.User;

public class UserTestFactory {
  public static User getUserTest(Long id) {
    User user = new User();
    user.setId(id);
    return user;
  }
}
