package com.tutorhub.repository;

import com.tutorhub.model.course.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Long> {
  Optional<Module> findByCourseIdAndId(Long courseId, Long moduleId);

  Page<Module> findAllByCourseId(Long courseId, Pageable page);

  @Query(
      value =
          """
              DELETE FROM module
              WHERE id = :moduleId
              AND course_id = :courseId
              """,
      nativeQuery = true)
  @Modifying(clearAutomatically = true)
  void deleteByCourseIdAndModuleId(Long courseId, Long moduleId);
}
