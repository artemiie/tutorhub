package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Module;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.repository.ContentRepository;
import com.tutorhub.repository.ModuleRepository;
import com.tutorhub.repository.SubmoduleRepository;
import com.tutorhub.service.SubmoduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmoduleServiceImpl implements SubmoduleService {
  private final SubmoduleRepository submoduleRepository;
  private final ModuleRepository moduleRepository;
  private final ContentRepository contentRepository;

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
    boolean exists =
        moduleRepository.existsByCourseIdAndId(courseId, moduleId);
    if (!exists) {
      throw new ResourceNotFoundException(
          ("Module with courseId[%s] and moduleId[%s] not found.").
              formatted(courseId, moduleId)
      );
    }

    exists =
        contentRepository.existsById(submodule.getContent().getId());
    if (!exists) {
      throw new ResourceNotFoundException(
          ("Content with id[%s] not found.").
              formatted(submodule.getContent().getId())
      );
    }

    Module module = new Module();
    module.setId(moduleId);

    submodule.setModule(module);
    return submoduleRepository.save(submodule);
  }

  @Override
  public Submodule update(final Long courseId,
                          final Long moduleId,
                          final Submodule entity) {
    Submodule existingModule = find(courseId, moduleId, entity.getId());
    existingModule.setName(entity.getName());
    return submoduleRepository.save(existingModule);
  }

  @Override
  public void delete(final Long courseId,
                     final Long moduleId,
                     final Long submoduleId) {
    submoduleRepository.
        deleteByModule_Course_IdAndModule_IdAndId(
            courseId, moduleId, submoduleId);
  }
}
