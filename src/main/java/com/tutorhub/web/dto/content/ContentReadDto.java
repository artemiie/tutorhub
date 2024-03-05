package com.tutorhub.web.dto.content;

import com.tutorhub.model.course.ContentType;

public record ContentReadDto(
    Long id,
    String url,
    String title,
    String description,
    ContentType contentType
) {
}
