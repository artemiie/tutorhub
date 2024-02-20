/*
package com.tutorhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@EqualsAndHashCode
public class Course {

  @Id private Long id;

  private String name;

  @ManyToOne
  @JoinTable(
      name = "users_courses",
      joinColumns = {@JoinColumn(name = "course_id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private User user;

  */
/* @OneToMany(mappedBy = "course")
private List<Module> modules;*//*

                               }
                               */
