package com.tutorhub.web.dto.submodule;

import jakarta.validation.constraints.NotNull;

public record SubmoduleCreateDto(
    @NotNull(message = "Name must be not null.")
    String name
) {
}
