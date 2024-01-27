package com.tutorhub.repository;

import com.tutorhub.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, ObjectId> {

  boolean existsByUsername(String username);

  boolean existsById(ObjectId id);

  Page<Student> findByRole(String role, Pageable pageable);
}
