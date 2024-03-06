package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Submodule;
import com.tutorhub.web.dto.submodule.SubmoduleCreateDto;
import com.tutorhub.web.dto.submodule.SubmoduleReadDto;
import com.tutorhub.web.dto.submodule.SubmoduleUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubmoduleMapper {
  SubmoduleReadDto toDto(Submodule submodule);

  @Mapping(target = "content.id", source = "contentId")
  Submodule fromDto(SubmoduleCreateDto submodule);

  Submodule fromDto(SubmoduleUpdateDto submodule);
}
