package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.Student;
import com.tutorhub.web.dto.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends Mappable<Student, StudentDTO> {
}
