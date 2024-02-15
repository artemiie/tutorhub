/*
package com.tutorhub.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "modules")
@Getter
@Setter
public class Module {

  @Id private Long id;

  private String name;

  @OneToMany
  @JoinTable(
      name = "sub_modules",
      joinColumns = {@JoinColumn(name = "module_id")},
      inverseJoinColumns = {@JoinColumn(name = "sub_module_id")})
  private List<Module> submodules;

  @OneToOne
  private TextContent textContent;
  @OneToOne
  private QuizContent quizContent;
  @OneToOne
  private VideoContent videoContent;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;
}
*/
