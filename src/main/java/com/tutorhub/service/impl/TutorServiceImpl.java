package com.tutorhub.service.impl;

import com.tutorhub.model.Tutor;
import com.tutorhub.model.exception.ResourceNotFoundException;
import com.tutorhub.repository.TutorRepository;
import com.tutorhub.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {
  private final TutorRepository tutorRepository;

  @Override
  public Tutor getById(final ObjectId id) {
    return tutorRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Tutor with id[" + id + "] not found."));
  }

  @Override
  public Page<Tutor> getAll(final Pageable page) {
    return tutorRepository.findAll(page);
  }

  @Override
  public Tutor update(final Tutor entity) {
    Tutor tutorOnDb =
        tutorRepository
            .findById(entity.getId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Tutor with username[" + entity.getUsername() + "] not found."));

    entity.setId(tutorOnDb.getId());
    entity.setUsername(tutorOnDb.getUsername());
    entity.setRole(tutorOnDb.getRole());

    return tutorRepository.save(entity);
  }

  @Override
  public boolean existsById(final ObjectId id) {
    return tutorRepository.existsById(id);
  }

  @Override
  public void delete(final ObjectId id) {
    tutorRepository.deleteById(id);
  }
}
