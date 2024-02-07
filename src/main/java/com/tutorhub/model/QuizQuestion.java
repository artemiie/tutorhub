package com.tutorhub.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class QuizQuestion {

  private ObjectId id;
  private String title;
  private List<String> options;
  private List<Integer> answerIndexes;
}
