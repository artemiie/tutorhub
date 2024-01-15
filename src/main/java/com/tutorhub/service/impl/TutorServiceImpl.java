package com.tutorhub.service.impl;

import com.tutorhub.model.Tutor;
import com.tutorhub.service.TutorService;
import com.tutorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {
  private final UserService userService;

  @Override
  public Tutor getById(final ObjectId id) {
    return null;
  }

  @Override
  public Page<Tutor> getAll(final Pageable page) {
    return userService.getAll(page).map(user -> (Tutor) user);
  }

  @Override
  public Tutor create(final Tutor entity) {
    return null;
  }

  @Override
  public Tutor update(final Tutor entity) {
    return null;
  }

  @Override
  public boolean existsById(final ObjectId id) {
    return false;
  }

  @Override
  public void delete(final ObjectId id) {

  }

}
