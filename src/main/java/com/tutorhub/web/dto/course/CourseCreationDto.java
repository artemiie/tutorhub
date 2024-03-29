package com.tutorhub.web.dto.course;

import jakarta.validation.constraints.NotNull;

public record CourseCreationDto(
    @NotNull(message = "Name must be not null.")
    String name,
    @NotNull(message = "User id must be not null.")
    Long userId
) {
}
