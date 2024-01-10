package com.tutorhub.repository;

import com.tutorhub.model.VideoContent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoContentRepository
        extends MongoRepository<VideoContent, ObjectId> {
}
