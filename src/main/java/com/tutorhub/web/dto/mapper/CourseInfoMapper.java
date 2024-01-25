package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.StudentCourseInfo;
import com.tutorhub.web.dto.CourseInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseInfoMapper extends Mappable<StudentCourseInfo, CourseInfoDTO> {}
