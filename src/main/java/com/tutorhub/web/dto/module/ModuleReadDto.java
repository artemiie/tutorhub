package com.tutorhub.web.dto.module;

import com.tutorhub.web.dto.submodule.SubmoduleReadDto;

import java.util.List;

public record ModuleReadDto(
    Long id,
    String name,
    List<SubmoduleReadDto> submodules
) {
}
