package com.tutorhub.repository;

import com.tutorhub.model.course.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
  boolean existsById(Long id);
}
