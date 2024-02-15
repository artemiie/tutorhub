package com.tutorhub.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "full_name")
  protected String fullName;

  @Column(name = "username")
  protected String username;

  @Column(name = "password")
  protected String password;

  /*@Column(name = "role")
  @OneToMany
  @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))*/
  @Enumerated(value = EnumType.STRING)
  protected Set<Role> role;

  @Column(name = "enabled")
  protected boolean enabled;

  /*@OneToMany(mappedBy = "user")
  @Fetch(FetchMode.JOIN)
  private List<Course> courses;*/

  /*@OneToMany(mappedBy = "user")
  private List<CourseInfo> assignedCourses;*/
}
