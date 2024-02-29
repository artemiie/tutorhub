package com.tutorhub.model.course;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProgressId implements Serializable {
  private Long moduleID;
  private Long submoduleID;
  private Long courseInfoID;
}
