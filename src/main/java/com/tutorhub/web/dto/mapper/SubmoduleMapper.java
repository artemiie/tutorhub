package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Submodule;
import com.tutorhub.web.dto.submodule.SubmoduleCreateDto;
import com.tutorhub.web.dto.submodule.SubmoduleReadDto;
import com.tutorhub.web.dto.submodule.SubmoduleUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubmoduleMapper {
  @Mapping(target = "contentReadDto.id", source = "content.id")
  @Mapping(target = "contentReadDto.fileId", source = "content.fileId")
  @Mapping(target = "contentReadDto.title", source = "content.title")
  @Mapping(
      target = "contentReadDto.description",
      source = "content.description")
  @Mapping(
      target = "contentReadDto.originalName",
      source = "content.originalName")
  @Mapping(target = "contentReadDto.type", source = "content.type")
  SubmoduleReadDto toDto(Submodule submodule);

  @Mapping(target = "content.id", source = "contentId")
  Submodule fromDto(SubmoduleCreateDto submodule);

  Submodule fromDto(SubmoduleUpdateDto submodule);
}
