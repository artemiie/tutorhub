/*
package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {

  @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
  @Null(message = "Id must be null.", groups = OnCreate.class)
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @NotNull(message = "Name must be not null.")
  private String name;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private TutorDTO tutor;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<ModuleDTO> modules;
}
*/
