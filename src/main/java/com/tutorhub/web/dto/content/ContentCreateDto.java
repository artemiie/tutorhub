package com.tutorhub.web.dto.content;

import com.tutorhub.model.course.ContentType;

import java.util.UUID;

public record ContentCreateDto(
    UUID fileId,
    String title,
    String description,
    String originalName,
    ContentType type
) {
}
