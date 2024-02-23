package com.tutorhub.repository;

import com.tutorhub.model.course.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  List<Course> findByCourseOwnerId(Long courseOwnerId);
}
