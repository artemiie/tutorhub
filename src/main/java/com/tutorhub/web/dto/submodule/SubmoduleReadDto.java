package com.tutorhub.web.dto.submodule;

import com.tutorhub.web.dto.content.ContentReadDto;

public record SubmoduleReadDto(
    Long id,
    String name,
    ContentReadDto contentReadDto
) {
}
