package com.tutorhub.repository;

import com.tutorhub.model.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {}
