package com.tutorhub.testfactory;

import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.model.course.Module;
import com.tutorhub.model.course.Progress;
import com.tutorhub.model.course.Submodule;

public class ProgressTestFactory {
  public static Progress getProgressTest(Module module,
                                         Submodule submodule,
                                         CourseInfo courseInfo) {
    Progress progress = new Progress();
    progress.setModule(module);
    progress.setSubmodule(submodule);
    progress.setCourseInfo(courseInfo);
    return progress;
  }
}
