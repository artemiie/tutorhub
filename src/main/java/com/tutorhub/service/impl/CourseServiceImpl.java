/*
package com.tutorhub.service.impl;

import com.tutorhub.model.Course;
import com.tutorhub.model.exception.ResourceNotFoundException;
import com.tutorhub.repository.CourseRepository;
import com.tutorhub.service.AuthService;
import com.tutorhub.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseRepository;
  private final AuthService authService;

  @Override
  public Course getById(final Long id) {
    return courseRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Course with id[" + id + "] not found."));
  }

  @Override
  public Page<Course> getAll(final Pageable page) {
    return courseRepository.findAll(page);
  }

  @Override
  public Course create(final Course entity) {
    return courseRepository.save(entity);
  }

  @Override
  public Course update(final Course entity) {
    return null;
  }

  @Override
  public boolean existsById(final Long id) {
    return false;
  }

  @Override
  public void delete(final Long id) {
    courseRepository.deleteById(id);
  }
}
*/
