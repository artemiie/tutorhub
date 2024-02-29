package com.tutorhub.service;

import com.tutorhub.model.course.CourseInfo;

public interface CourseInfoService {
  CourseInfo findByUserIdAndCourseId(Long userId, Long courseId);

  CourseInfo update(CourseInfo courseInfo);

  void create(CourseInfo courseInfo);
}
