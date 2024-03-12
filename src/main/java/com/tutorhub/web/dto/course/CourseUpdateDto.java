package com.tutorhub.web.dto.course;

import jakarta.validation.constraints.NotNull;

public record CourseUpdateDto(
    @NotNull(message = "Id must be not null.")
    Long id,
    @NotNull(message = "Name must be not null.")
    String name
) {
}
