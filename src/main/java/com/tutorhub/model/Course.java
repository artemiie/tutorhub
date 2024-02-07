package com.tutorhub.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("courses")
@Getter
@Setter
public class Course {

  @MongoId private ObjectId id;

  private String name;
  @DBRef private Tutor tutor;
  private List<Module> modules;
  private List<Student> students;
}
