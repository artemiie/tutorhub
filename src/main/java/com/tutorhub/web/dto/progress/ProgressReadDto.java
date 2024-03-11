package com.tutorhub.web.dto.progress;

import com.tutorhub.web.dto.module.ModuleReadDto;

import java.util.List;

public record ProgressReadDto(
    List<ModuleReadDto> modules
) {
}
