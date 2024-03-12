package com.tutorhub.service;

import com.tutorhub.model.course.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModuleService {
  Module find(Long courseId, Long moduleId);

  Page<Module> findAllPaged(Long courseId, Pageable page);

  Module create(Long courseId, Module module);

  Module update(Long courseId, Module module);

  void delete(Long courseId, Long moduleId);
}
