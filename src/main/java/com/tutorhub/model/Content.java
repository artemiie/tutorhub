package com.tutorhub.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
public abstract class Content {

  @MongoId protected ObjectId id;
}
