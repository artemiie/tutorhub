package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProgressDTO {
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<ModuleDTO> moduleDTOS = new ArrayList<>();
}
