package com.tutorhub.testfactory;

import com.tutorhub.model.course.Submodule;

public class SubmoduleTestFactory {
  public static Submodule getSubmoduleTest(Long id) {
    Submodule submodule = new Submodule();
    submodule.setId(id);
    submodule.setName("submodule");
    return submodule;
  }
}
