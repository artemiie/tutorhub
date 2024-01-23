package com.tutorhub.repository;

import com.tutorhub.model.Student;
import com.tutorhub.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, ObjectId> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
