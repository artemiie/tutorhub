package com.tutorhub.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User {

  private List<StudentCourseInfo> coursesInfo;

  public Student() {
    this.role = "ROLE_STUDENT";
    this.coursesInfo = new ArrayList<>();
  }
}
