package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Module;
import com.tutorhub.repository.ModuleRepository;
import com.tutorhub.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {
  private final ModuleRepository moduleRepository;

  @Override
  public Module getById(final Long id) {
    return moduleRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Module with id [" + id + "] not found"));
  }

  @Override
  public Page<Module> getAll(final Pageable page) {
    return moduleRepository.findAll(page);
  }

  @Override
  public Module create(final Module entity) {
    return moduleRepository.save(entity);
  }

  @Override
  public Module update(final Module entity) {
    Module existingModule = getById(entity.getId());
    existingModule.setName(entity.getName());
    existingModule.setCourse(entity.getCourse());
    existingModule.setContent(entity.getContent());
    return moduleRepository.save(existingModule);
  }

  @Override
  public boolean existsById(Long id) {
    return moduleRepository.existsById(id);
  }

  @Override
  public void delete(Long id) {
    moduleRepository.deleteById(id);
  }
}
