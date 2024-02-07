package com.tutorhub.repository;

import com.tutorhub.model.Tutor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorRepository extends MongoRepository<Tutor, ObjectId> {

  boolean existsByUsername(String username);

  boolean existsById(ObjectId id);

  Page<Tutor> findByRole(String role, Pageable pageable);
}
