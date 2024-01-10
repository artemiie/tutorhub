package com.tutorhub.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("quiz_contents")
@Getter
@Setter
public class QuizContent extends Content {

    private List<QuizQuestion> questions;

}
