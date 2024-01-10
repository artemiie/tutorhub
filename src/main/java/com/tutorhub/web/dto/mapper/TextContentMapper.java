package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.TextContent;
import com.tutorhub.web.dto.TextContentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TextContentMapper
        extends Mappable<TextContent, TextContentDTO> {
}
