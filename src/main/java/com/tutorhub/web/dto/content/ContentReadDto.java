package com.tutorhub.web.dto.content;

import com.tutorhub.model.course.ContentType;

import java.util.UUID;

public record ContentReadDto(
    Long id,
    UUID fileId,
    String originalName,
    String title,
    String description,
    ContentType type
) {
}
