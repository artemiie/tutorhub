package com.tutorhub.repository;

import com.tutorhub.model.Progress;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProgressRepository extends MongoRepository<Progress, ObjectId> {}
