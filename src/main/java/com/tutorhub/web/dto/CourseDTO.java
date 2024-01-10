package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class CourseDTO {

    @NotNull(
            message = "Id must be not null.",
            groups = OnUpdate.class
    )
    @Null(
            message = "Id must be null.",
            groups = OnCreate.class
    )
    private ObjectId id;

    @NotNull(
            message = "Name must be not null."
    )
    private String name;

    @JsonProperty(
            access = JsonProperty.Access.READ_ONLY
    )
    private TutorDTO tutor;

    @JsonProperty(
            access = JsonProperty.Access.READ_ONLY
    )
    private List<ModuleDTO> modules;

}
