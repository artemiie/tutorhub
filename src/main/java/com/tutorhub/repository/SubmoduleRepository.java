package com.tutorhub.repository;

import com.tutorhub.model.course.Submodule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubmoduleRepository extends JpaRepository<Submodule, Long> {
  @Query(
      value =
          """
              SELECT s.id,
                     s.name,
                     s.module_id,
                     s.content_id
              FROM submodule s LEFT JOIN module m ON s.module_id = m.id
              LEFT JOIN course c ON m.course_id = c.id
              WHERE s.id = :submoduleId
              AND m.id = :moduleId
              AND c.id = :courseId
              """,
      nativeQuery = true)
  Optional<Submodule> findByCourseIdAndModuleIdAndSubmoduleId(Long courseId,
                                                              Long moduleId,
                                                              Long submoduleId);
}
