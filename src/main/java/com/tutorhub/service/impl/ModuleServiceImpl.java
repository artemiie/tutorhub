package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Course;
import com.tutorhub.model.course.Module;
import com.tutorhub.repository.ModuleRepository;
import com.tutorhub.service.CourseService;
import com.tutorhub.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {
  private final ModuleRepository moduleRepository;
  private final CourseService courseService;

  @Override
  public Module find(Long courseId, Long moduleId) {
    return moduleRepository
        .findByCourseIdAndId(courseId, moduleId)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Module with id = %s not found on Course with id = %s"
                        .formatted(moduleId, courseId)));
  }

  @Override
  public Page<Module> findAllPaged(Long courseId, Pageable page) {
    return moduleRepository.findAllByCourseId(courseId, page);
  }

  @Override
  public Module create(Long courseId, final Module entity) {
    Course course = courseService.getById(courseId);
    entity.setCourse(course);
    return moduleRepository.save(entity);
  }

  @Override
  public Module update(Long courseId, final Module entity) {
    Module existingModule = find(courseId, entity.getId());
    existingModule.setName(entity.getName());
    return moduleRepository.save(existingModule);
  }

  @Override
  @Transactional
  public void delete(Long courseId, Long moduleId) {
    moduleRepository.deleteByCourseIdAndModuleId(courseId, moduleId);
  }
}
