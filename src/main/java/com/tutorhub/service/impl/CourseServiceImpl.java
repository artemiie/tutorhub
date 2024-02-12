package com.tutorhub.service.impl;

import com.tutorhub.model.Course;
import com.tutorhub.model.exception.ResourceNotFoundException;
import com.tutorhub.repository.CourseRepository;
import com.tutorhub.service.AuthService;
import com.tutorhub.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseRepository;
  private final AuthService authService;

  @Override
  public Course getById(final ObjectId id) {
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
    Course courseOnDb =
        courseRepository
            .findById(entity.getId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Course with id [" + entity.getId() + "] not found."));
    entity.setId(courseOnDb.getId());
    entity.setName(courseOnDb.getName());
    entity.setTutor(courseOnDb.getTutor());
    entity.setModules(courseOnDb.getModules());
    entity.setStudents(courseOnDb.getStudents());

    return courseRepository.save(entity);
  }

  @Override
  public boolean existsById(final ObjectId id) {
    return false;
  }

  @Override
  public void delete(final ObjectId id) {
    courseRepository.deleteById(id);
  }
}
