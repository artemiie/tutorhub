package com.tutorhub.web.dto.module;

import jakarta.validation.constraints.NotNull;

public record ModuleCreateDto(
    @NotNull(message = "Name must be not null.")
    String name
) {
}
