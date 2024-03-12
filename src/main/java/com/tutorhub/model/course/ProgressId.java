package com.tutorhub.model.course;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProgressId implements Serializable {
  private Module module;
  private Submodule submodule;
  private CourseInfo courseInfo;
}
