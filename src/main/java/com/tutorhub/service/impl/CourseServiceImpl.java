package com.tutorhub.service.impl;

import com.tutorhub.model.Course;
import com.tutorhub.model.CourseInfo;
import com.tutorhub.model.Progress;
import com.tutorhub.model.User;
import com.tutorhub.model.exception.ResourceNotFoundException;
import com.tutorhub.repository.CourseRepository;
import com.tutorhub.service.CourseInfoService;
import com.tutorhub.service.CourseService;
import com.tutorhub.service.ProgressService;
import com.tutorhub.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseRepository;
  private final UserService userService;
  private final CourseInfoService courseInfoService;
  private final ProgressService progressService;

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
    Course courseOnDb = getById(entity.getId());
    entity.setCourseOwner(courseOnDb.getCourseOwner());
    return courseRepository.save(entity);
  }

  @Override
  public boolean existsById(final Long id) {
    return false;
  }

  @Override
  public void delete(final Long id) {
    courseRepository.deleteById(id);
  }

  @Override
  public void assignUser(Long userId, Long courseId) {

    Course course = getById(courseId);
    User user = userService.getById(userId);
    Progress progress = progressService.create(new Progress());

    CourseInfo courseInfo = new CourseInfo();
    courseInfo.setCourse(course);
    courseInfo.setUser(user);
    courseInfo.setProgress(progress);

    courseInfoService.create(courseInfo);
  }

  @Override
  public List<Course> findByUserId(Long userId) {
    return courseRepository.findByCourseOwnerId(userId);
  }
}
