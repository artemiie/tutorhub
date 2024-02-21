package com.tutorhub.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "fullname")
  protected String fullname;

  @Column(name = "username")
  protected String username;

  @Column(name = "password")
  protected String password;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  protected Set<Role> role = new HashSet<>();

  @Column(name = "enabled")
  protected boolean enabled;

  public User() {}
}
