package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Submodule;
import com.tutorhub.web.dto.submodule.SubmoduleCreateDto;
import com.tutorhub.web.dto.submodule.SubmoduleReadDto;
import com.tutorhub.web.dto.submodule.SubmoduleUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubmoduleMapper {
  SubmoduleReadDto toDto(Submodule submodule);

  Submodule fromDto(SubmoduleCreateDto submodule);

  Submodule fromDto(SubmoduleUpdateDto submodule);
}
