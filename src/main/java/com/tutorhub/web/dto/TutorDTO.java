package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorDTO extends UserDTO {

    @JsonProperty(
            access = JsonProperty.Access.READ_ONLY
    )
    private List<CourseDTO> courses;

}
