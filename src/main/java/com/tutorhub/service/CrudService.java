package com.tutorhub.service;

public interface CrudService<C> {

  // C getById(Long id);
  //
  // Page<C> getAll(Pageable page);

  C create(C entity);

  // C update(C entity);

  // boolean existsById(Long id);

  // void delete(Long id);
}
