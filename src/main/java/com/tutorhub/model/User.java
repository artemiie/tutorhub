package com.tutorhub.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("users")
@Getter
@Setter
public abstract class User {

  @MongoId protected ObjectId id;
  protected String fullName;

  @Indexed(unique = true)
  protected String username;

  protected String password;

  protected String role;
}
