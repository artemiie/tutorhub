package com.tutorhub.repository;

import com.tutorhub.model.course.Submodule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmoduleRepository extends JpaRepository<Submodule, Long> {

  Optional<Submodule> findByModule_Course_IdAndModule_IdAndId(
      Long courseId, Long moduleId, Long submoduleId);

  Page<Submodule> findByModule_Course_IdAndModule_Id(
      Long courseId, Long moduleId, Pageable page);
}
