package com.tutorhub.repository;

import com.tutorhub.model.course.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {}
