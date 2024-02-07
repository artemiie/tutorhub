package com.tutorhub.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonUserService<T> {
  T getById(ObjectId id);

  Page<T> getAll(Pageable page);

  T update(T entity);

  boolean existsById(ObjectId id);

  void delete(ObjectId id);
}
