/*
package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Content;
import com.tutorhub.model.QuizContent;
import com.tutorhub.model.TextContent;
import com.tutorhub.model.VideoContent;
import com.tutorhub.web.dto.ContentDTO;
import com.tutorhub.web.dto.QuizContentDTO;
import com.tutorhub.web.dto.TextContentDTO;
import com.tutorhub.web.dto.VideoContentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContentMapper {

  default Content fromDto(final ContentDTO d) {
    if (d == null) {
      return null;
    } else if (d instanceof TextContentDTO) {
      return Mappers.getMapper(TextContentMapper.class).fromDto((TextContentDTO) d);
    } else if (d instanceof VideoContentDTO) {
      return Mappers.getMapper(VideoContentMapper.class).fromDto((VideoContentDTO) d);
    } else if (d instanceof QuizContentDTO) {
      return Mappers.getMapper(QuizContentMapper.class).fromDto((QuizContentDTO) d);
    } else {
      return null;
    }
  }

  default ContentDTO toDto(final Content e) {
    if (e == null) {
      return null;
    } else if (e instanceof TextContent) {
      return Mappers.getMapper(TextContentMapper.class).toDto((TextContent) e);
    } else if (e instanceof VideoContent) {
      return Mappers.getMapper(VideoContentMapper.class).toDto((VideoContent) e);
    } else if (e instanceof QuizContent) {
      return Mappers.getMapper(QuizContentMapper.class).toDto((QuizContent) e);
    } else {
      return null;
    }
  }
}
*/
