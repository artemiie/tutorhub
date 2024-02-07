package com.tutorhub.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("student_courses_info")
@Getter
@Setter
public class StudentCourseInfo {

  @MongoId private ObjectId id;

  private Student user;
  private Course course;
  private Progress progress;
}
