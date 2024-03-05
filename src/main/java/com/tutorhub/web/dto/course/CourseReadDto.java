package com.tutorhub.web.dto.course;

import com.tutorhub.web.dto.module.ModuleReadDto;
import com.tutorhub.web.dto.user.UserReadDto;

import java.util.List;

public record CourseReadDto(
    Long id,
    String name,
    UserReadDto courseOwner,
    List<ModuleReadDto> modules
) {
}
