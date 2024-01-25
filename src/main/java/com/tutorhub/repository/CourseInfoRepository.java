package com.tutorhub.repository;

import com.tutorhub.model.StudentCourseInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseInfoRepository extends MongoRepository<StudentCourseInfo, ObjectId> {}
