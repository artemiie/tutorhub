package com.tutorhub.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("progress")
@Getter
@Setter
public class Progress {

  @MongoId private ObjectId id;

  private List<Module> passedModules;
}
