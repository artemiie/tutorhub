package com.tutorhub.repository;

import com.tutorhub.model.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
  List<Course> findByCourseOwnerId(Long courseOwnerId);

  Page<Course> findByCourseOwnerId(Long courseOwnerId, Pageable page);

}
