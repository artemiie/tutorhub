package com.tutorhub.service.impl;

import com.tutorhub.model.course.Progress;
import com.tutorhub.repository.ProgressRepository;
import com.tutorhub.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {
  public final ProgressRepository progressRepository;

  @Override
  public Progress getById(final Long id) {
    return null;
  }

  @Override
  public Page<Progress> getAll(final Pageable page) {
    return null;
  }

  @Override
  public Progress create(final Progress entity) {
    return progressRepository.save(entity);
  }

  @Override
  public Progress update(final Progress entity) {
    return null;
  }

  @Override
  public boolean existsById(final Long id) {
    return false;
  }

  @Override
  public void delete(final Long id) {
  }
}
