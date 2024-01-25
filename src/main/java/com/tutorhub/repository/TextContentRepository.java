package com.tutorhub.repository;

import com.tutorhub.model.TextContent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TextContentRepository extends MongoRepository<TextContent, ObjectId> {}
