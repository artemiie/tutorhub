package com.tutorhub.testfactory;

import com.tutorhub.model.Course;
import org.bson.types.ObjectId;

public class CourseTestFactory {
  public static Course getCourseTest(ObjectId id) {
    Course course = new Course();
    course.setId(id);
    course.setName("Test Course");
    return course;
  }
}
