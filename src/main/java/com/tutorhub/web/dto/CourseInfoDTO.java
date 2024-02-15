package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseInfoDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private StudentDTO user;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private CourseDTO course;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private ProgressDTO progress;
}
