package com.tutorhub.service;

import com.tutorhub.model.course.Submodule;

public interface SubmoduleService {

  Submodule create(Long courseId, Long moduleId, Submodule submodule);
}
