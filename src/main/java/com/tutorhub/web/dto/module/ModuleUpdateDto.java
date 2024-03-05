package com.tutorhub.web.dto.module;

import jakarta.validation.constraints.NotNull;

public record ModuleUpdateDto(
    @NotNull(message = "Id must be not null.")
    Long id,
    @NotNull(message = "Name must be not null.")
    String name
) {
}
