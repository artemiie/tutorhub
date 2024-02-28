package com.tutorhub.service;

import com.tutorhub.model.course.Course;

import java.util.List;

public interface CourseService extends CrudService<Course> {
  void assignUser(Long userId, Long courseId);

  List<Course> findByUserId(Long userId);
}
