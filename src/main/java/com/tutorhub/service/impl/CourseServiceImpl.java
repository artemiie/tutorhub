package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Course;
import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.model.user.User;
import com.tutorhub.repository.CourseRepository;
import com.tutorhub.service.CourseInfoService;
import com.tutorhub.service.CourseService;
import com.tutorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseRepository;
  private final UserService userService;
  private final CourseInfoService courseInfoService;

  @Override
  public Course find(final Long id) {
    return courseRepository
        .findById(id)
        .orElseThrow(() ->
            new ResourceNotFoundException(
                "Course with id[%s] not found.".formatted(id)));
  }

  @Override
  public Page<Course> findAll(final Pageable page) {
    return courseRepository.findAll(page);
  }

  @Override
  public Page<Course> findAllByUser(final Long userId, final Pageable page) {
    if (userId == null) {
      return courseRepository.findAll(page);
    }
    return courseRepository.findByCourseOwnerId(userId, page);
  }

  @Override
  public Course create(final Course entity) {
    return courseRepository.save(entity);
  }

  @Override
  public Course update(final Course entity) {
    Course courseOnDb = find(entity.getId());
    entity.setCourseOwner(courseOnDb.getCourseOwner());
    return courseRepository.save(entity);
  }

  @Override
  public void delete(final Long id) {
    courseRepository.deleteById(id);
  }

  @Override
  public void assignUser(final Long userId, final Long courseId) {
    Course course = find(courseId);
    User user = userService.find(userId);

    CourseInfo courseInfo = new CourseInfo();
    courseInfo.setCourse(course);
    courseInfo.setUser(user);

    courseInfoService.create(courseInfo);
  }

  @Override
  public List<Course> findByUserId(final Long userId) {
    return courseRepository.findByCourseOwnerId(userId);
  }
}
