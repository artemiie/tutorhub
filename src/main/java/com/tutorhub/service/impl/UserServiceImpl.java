package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.user.User;
import com.tutorhub.repository.UserRepository;
import com.tutorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User find(final Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(ResourceNotFoundException::new);
  }

  public User findByUsername(final String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(ResourceNotFoundException::new);
  }

  public Page<User> findAll(final Pageable page) {
    return userRepository.findAll(page);
  }

  @Override
  public User create(final User entity) {
    return userRepository.save(entity);
  }

  @Override
  public boolean existsByUsername(final String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public User update(final User user) {
    User userOnDb = find(user.getId());

    userOnDb.setFullname(user.getFullname());

    return userRepository.save(userOnDb);
  }

  @Override
  public boolean existsById(final Long id) {
    return userRepository.existsById(id);
  }

  @Override
  public void enable(final String username) {
    findByUsername(username);
    userRepository.enable(username);
  }

  @Override
  public void delete(final Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public boolean isCourseOwner(final Long userId, final Long courseId) {
    return userRepository.isCourseOwner(userId, courseId);
  }
}
