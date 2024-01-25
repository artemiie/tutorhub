package com.tutorhub.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public abstract class Content {

  @Id protected ObjectId id;
}
