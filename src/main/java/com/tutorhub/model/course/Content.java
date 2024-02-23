package com.tutorhub.model.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "content")
@Getter
@Setter
public class Content {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "url")
  private String url;

  @Column(name = "content_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private ContentType contentType;

}
