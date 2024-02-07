package com.tutorhub.repository;

import com.tutorhub.model.User;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  Page<User> findAll(Pageable page);
}
