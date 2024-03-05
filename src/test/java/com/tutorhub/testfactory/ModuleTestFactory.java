package com.tutorhub.testfactory;

import com.tutorhub.model.course.Module;

public class ModuleTestFactory {
  public static Module getModuleTest(Long id) {
    Module module = new Module();
    module.setId(id);
    module.setName("Test Module");
    return module;
  }
}
