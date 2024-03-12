package com.tutorhub.model.course;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "progress")
@Getter
@Setter
@IdClass(ProgressId.class)
public class Progress {

  @Id
  @ManyToOne
  @JoinColumn(
      name = "module_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private Module module;

  @Id
  @ManyToOne
  @JoinColumn(
      name = "submodule_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private Submodule submodule;

  @Id
  @ManyToOne
  @JoinColumn(
      name = "course_info_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private CourseInfo courseInfo;
}
