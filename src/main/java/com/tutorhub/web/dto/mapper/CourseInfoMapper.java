package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.web.dto.courseinfo.CourseInfoReadDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseInfoMapper {
  CourseInfoReadDto toCourseInfoReadDto(CourseInfo courseInfo);
}

