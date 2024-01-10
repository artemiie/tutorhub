package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.Tutor;
import com.tutorhub.web.dto.TutorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TutorMapper extends Mappable<Tutor, TutorDTO> {

}
