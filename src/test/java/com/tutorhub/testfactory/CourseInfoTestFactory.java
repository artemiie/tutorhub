package com.tutorhub.testfactory;

import com.tutorhub.model.course.CourseInfo;

public class CourseInfoTestFactory {
  public static CourseInfo getCourseInfoTest(Long id) {
    CourseInfo courseInfo = new CourseInfo();
    courseInfo.setId(id);
    return courseInfo;
  }
}
