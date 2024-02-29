package com.tutorhub.repository;

import com.tutorhub.model.course.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {
  Optional<CourseInfo> findByUserIdAndCourseId(Long userId, Long courseId);
}
