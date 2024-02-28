package com.tutorhub.service;

import com.tutorhub.model.course.Submodule;

public interface SubmoduleService {

  Submodule find(Long courseId, Long moduleId, Long submoduleId);

  Submodule create(Long courseId, Long moduleId, Submodule submodule);
}
