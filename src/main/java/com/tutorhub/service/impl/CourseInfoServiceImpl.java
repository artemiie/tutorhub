package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.repository.CourseInfoRepository;
import com.tutorhub.service.CourseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseInfoServiceImpl implements CourseInfoService {
  private final CourseInfoRepository courseInfoRepository;

  public CourseInfo update(final CourseInfo courseInfo) {
    return courseInfoRepository.save(courseInfo);
  }

  @Override
  public void create(final CourseInfo courseInfo) {
    courseInfoRepository.save(courseInfo);
  }

  public CourseInfo findByUserIdAndCourseId(final Long userId,
                                            final Long courseId) {
    return
        courseInfoRepository
            .findByUserIdAndCourseId(userId, courseId)
            .orElseThrow(ResourceNotFoundException::new);
  }
}
