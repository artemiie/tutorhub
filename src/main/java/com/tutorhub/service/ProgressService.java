package com.tutorhub.service;

import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.model.course.Progress;

import java.util.List;

public interface ProgressService {

  void create(Long userId,
              Long courseId,
              Long moduleId,
              Long submoduleId);

  List<Progress> findByCourseInfo(CourseInfo courseEntity);
}
