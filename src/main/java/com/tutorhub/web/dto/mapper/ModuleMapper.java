package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Module;
import com.tutorhub.web.dto.module.ModuleCreateDto;
import com.tutorhub.web.dto.module.ModuleReadDto;
import com.tutorhub.web.dto.module.ModuleUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
  ModuleReadDto toDto(Module module);

  Module fromDto(ModuleCreateDto moduleReadDto);

  Module fromDto(ModuleUpdateDto moduleReadDto);
}
