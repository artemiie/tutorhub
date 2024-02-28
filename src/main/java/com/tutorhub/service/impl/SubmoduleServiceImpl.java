package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Module;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.repository.SubmoduleRepository;
import com.tutorhub.service.ModuleService;
import com.tutorhub.service.SubmoduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmoduleServiceImpl implements SubmoduleService {
  private final SubmoduleRepository submoduleRepository;
  private final ModuleService moduleService;

  @Override
  public Submodule find(final Long courseId,
                        final Long moduleId,
                        final Long submoduleId) {
    return submoduleRepository
        .findByModule_Course_IdAndModule_IdAndId(
            courseId, moduleId, submoduleId)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    ("Submodule with id = %s not found on Module with id = %s"
                        + " and on Course with id %s")
                        .formatted(submoduleId, moduleId, courseId)));
  }

  @Override
  public Page<Submodule> findAllPaged(final Long courseId,
                                      final Long moduleId,
                                      final Pageable page) {
    return submoduleRepository
        .findByModule_Course_IdAndModule_Id(courseId, moduleId, page);
  }

  @Override
  public Submodule create(final Long courseId,
                          final Long moduleId,
                          final Submodule submodule) {
    Module module = moduleService.find(courseId, moduleId);
    submodule.setModule(module);
    return submoduleRepository.save(submodule);
  }
}
