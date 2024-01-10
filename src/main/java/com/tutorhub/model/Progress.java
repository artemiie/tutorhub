package com.tutorhub.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("progress")
@Getter
@Setter
public class Progress {

  @Id private ObjectId id;

  private List<Module> passedModules;
}
