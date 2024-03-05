package com.tutorhub.web.dto.submodule;

import com.tutorhub.web.dto.content.ContentDTO;

public record SubmoduleReadDto(
    Long id,
    String name,
    ContentDTO content
) {
}
