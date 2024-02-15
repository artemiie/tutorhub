/*
package com.tutorhub.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User {
  @OneToMany(mappedBy = "user")
  private List<CourseInfo> coursesInfo;

  public Student() {
    this.role = "ROLE_STUDENT";
    this.coursesInfo = new ArrayList<>();
  }
}
*/
