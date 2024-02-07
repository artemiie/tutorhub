package com.tutorhub.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("text_contents")
@Getter
@Setter
public class TextContent extends Content {

  private String text;
}
