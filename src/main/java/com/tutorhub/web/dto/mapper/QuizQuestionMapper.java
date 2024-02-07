package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.QuizQuestion;
import com.tutorhub.web.dto.QuizQuestionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizQuestionMapper extends Mappable<QuizQuestion, QuizQuestionDTO> {}
