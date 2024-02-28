package com.tutorhub.service.impl;

import com.tutorhub.model.course.Module;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.repository.SubmoduleRepository;
import com.tutorhub.service.ModuleService;
import com.tutorhub.service.SubmoduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmoduleServiceImpl implements SubmoduleService {
  private final SubmoduleRepository submoduleRepository;
  private final ModuleService moduleService;

  @Override
  public Submodule create(Long courseId, Long moduleId, Submodule submodule) {
    Module module = moduleService.find(courseId, moduleId);
    submodule.setModule(module);
    return submoduleRepository.save(submodule);
  }
}
