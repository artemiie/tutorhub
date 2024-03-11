package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Course;
import com.tutorhub.web.dto.course.CourseCreationDto;
import com.tutorhub.web.dto.course.CourseReadDto;
import com.tutorhub.web.dto.course.CourseUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
  CourseReadDto toCourseReadDto(Course course);
  @Mapping(
      target = "courseOwner.id",
      source = "userId")
  Course fromDto(CourseCreationDto courseCreationDto);

  Course fromDto(CourseUpdateDto courseUpdateDto);
}
