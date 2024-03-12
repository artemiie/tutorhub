package com.tutorhub.web.dto.submodule;

import jakarta.validation.constraints.NotNull;

public record SubmoduleUpdateDto(
    @NotNull(message = "Id must be not null.")
    Long id,
    @NotNull(message = "Name must be not null.")
    String name
) {
}
