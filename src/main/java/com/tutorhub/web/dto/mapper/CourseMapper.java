package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Course;
import com.tutorhub.web.dto.CourseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper extends Mappable<Course, CourseDTO> {}
