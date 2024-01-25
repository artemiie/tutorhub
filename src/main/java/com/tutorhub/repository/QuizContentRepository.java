package com.tutorhub.repository;

import com.tutorhub.model.QuizContent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizContentRepository extends MongoRepository<QuizContent, ObjectId> {}
