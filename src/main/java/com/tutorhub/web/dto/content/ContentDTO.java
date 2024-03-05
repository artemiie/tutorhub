package com.tutorhub.web.dto.content;

import com.tutorhub.model.course.ContentType;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record ContentDTO(
    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    @Null(message = "Id must be null.", groups = OnCreate.class)
    Long id,
    String url,
    String title,
    String description,
    ContentType contentType
) {
}
