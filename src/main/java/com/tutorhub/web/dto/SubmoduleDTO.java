package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmoduleDTO {

  @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
  @Null(message = "Id must be null.", groups = OnCreate.class)
  private Long id;

  @NotNull(message = "Name must be not null.")
  private String name;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private ContentDTO content;
}
