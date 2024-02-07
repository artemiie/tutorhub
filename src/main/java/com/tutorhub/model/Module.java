package com.tutorhub.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("modules")
@Getter
@Setter
public class Module {

  @MongoId private ObjectId id;

  private String name;
  private Content content;
  private List<Module> submodules;
  private Course course;
}
