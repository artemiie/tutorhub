package com.tutorhub.model.course;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "progress")
@Getter
@Setter
public class Progress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "submodule_id")
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "submodule_id")
  private List<Submodule> passedModules;
}
