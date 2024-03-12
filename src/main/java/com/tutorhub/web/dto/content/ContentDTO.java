package com.tutorhub.web.dto.content;

import com.tutorhub.model.course.ContentType;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ContentDTO(
    @NotNull(message = "Id must be not null.")
    Long id,
    UUID fileId,
    String originalName,
    String title,
    String description,
    ContentType type
) {
}
