package com.tutorhub.web.dto.content;

import com.tutorhub.model.course.ContentType;
import jakarta.validation.constraints.NotNull;

public record ContentDTO(
    @NotNull(message = "Id must be not null.")
    Long id,
    String url,
    String title,
    String description,
    ContentType contentType
) {
}
