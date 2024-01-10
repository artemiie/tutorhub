package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.QuizContent;
import com.tutorhub.web.dto.QuizContentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizContentMapper
        extends Mappable<QuizContent, QuizContentDTO> {
}
