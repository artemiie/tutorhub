package com.tutorhub.model.course;

import jakarta.persistence.Column;
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
  @Column(name = "module_id", nullable = false)
  private Long moduleID;

  @Id
  @Column(name = "submodule_id", nullable = false)
  private Long submoduleID;

  @Id
  @Column(name = "course_info_id", nullable = false)
  private Long courseInfoID;

  @ManyToOne
  @JoinColumn(
      name = "module_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private Module module;

  @ManyToOne
  @JoinColumn(
      name = "submodule_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private Submodule submodule;

  @ManyToOne
  @JoinColumn(
      name = "course_info_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private CourseInfo courseInfo;
}
