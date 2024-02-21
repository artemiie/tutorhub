package com.tutorhub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses_info")
@Getter
@Setter
public class CourseInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_id")
  private Course course;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "progress_id")
  private Progress progress;
}
