package com.tutorhub.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonUserService<T> {
  T getById(Long id);

  Page<T> getAll(Pageable page);

  T update(T entity);

  boolean existsById(Long id);

  void delete(Long id);
}
