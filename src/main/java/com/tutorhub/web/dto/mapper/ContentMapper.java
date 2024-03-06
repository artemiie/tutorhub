package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Content;
import com.tutorhub.web.dto.content.ContentCreateDto;
import com.tutorhub.web.dto.content.ContentReadDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContentMapper {
  Content fromDto(ContentCreateDto createDto);

  ContentReadDto toDto(Content content);
}
