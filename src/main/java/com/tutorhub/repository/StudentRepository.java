package com.tutorhub.repository;

import com.tutorhub.model.Student;
import com.tutorhub.model.User;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, ObjectId> {

  boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);
}
