package com.tutorhub.repository;

import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.model.course.Progress;
import com.tutorhub.model.course.ProgressId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository
    extends JpaRepository<Progress, ProgressId> {
  List<Progress> findByCourseInfo(CourseInfo courseEntity);
}
