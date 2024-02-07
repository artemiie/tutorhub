package com.tutorhub.service.impl;

import com.tutorhub.model.Course;
import com.tutorhub.model.Tutor;
import com.tutorhub.model.User;
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
    return null;
  }

  @Override
  public Page<Course> getAll(final Pageable page) {
    return null;
  }

  @Override
  public Course create(final Course entity) {
    User user = authService.getCurrentLoggedUser();
    entity.setTutor((Tutor) user);
    return courseRepository.save(entity);
  }

  @Override
  public Course update(final Course entity) {
    return null;
  }

  @Override
  public boolean existsById(final ObjectId id) {
    return false;
  }

  @Override
  public void delete(final ObjectId id) {}
}
