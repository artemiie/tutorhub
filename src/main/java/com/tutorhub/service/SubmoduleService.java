package com.tutorhub.service;

import com.tutorhub.model.course.Submodule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubmoduleService {

  Submodule find(Long courseId, Long moduleId, Long submoduleId);

  Submodule create(Long courseId, Long moduleId, Submodule submodule);

  Page<Submodule> findAllPaged(Long courseId, Long moduleId, Pageable page);
}
