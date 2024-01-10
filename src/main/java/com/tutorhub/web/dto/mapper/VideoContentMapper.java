package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.VideoContent;
import com.tutorhub.web.dto.VideoContentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoContentMapper
        extends Mappable<VideoContent, VideoContentDTO> {
}
