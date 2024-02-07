package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class CourseInfoDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private ObjectId id;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private StudentDTO user;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private CourseDTO course;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private ProgressDTO progress;
}
