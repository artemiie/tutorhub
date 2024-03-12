package com.tutorhub.service;

import com.tutorhub.model.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService extends CrudService<Course> {
  void assignUser(Long userId, Long courseId);

  List<Course> findByUserId(Long userId);

  Page<Course> findAllByUser(Long userId, Pageable page);
}
