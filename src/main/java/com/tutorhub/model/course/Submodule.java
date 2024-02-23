package com.tutorhub.model.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "submodule")
@Getter
@Setter
public class Submodule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "module_id")
  private Module module;
}
