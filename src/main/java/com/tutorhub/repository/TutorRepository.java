package com.tutorhub.repository;

import com.tutorhub.model.Tutor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorRepository extends MongoRepository<Tutor, ObjectId> {
  boolean existsByUsername(String username);
}
