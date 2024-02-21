package com.tutorhub.service;

import com.tutorhub.model.Course;

public interface CourseService extends CrudService<Course> {
  void assignUser(Long userId, Long courseId);
}
