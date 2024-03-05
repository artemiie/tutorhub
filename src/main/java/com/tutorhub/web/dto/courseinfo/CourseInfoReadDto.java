package com.tutorhub.web.dto.courseinfo;

import com.tutorhub.web.dto.course.CourseReadDto;
import com.tutorhub.web.dto.progress.ProgressReadDto;
import com.tutorhub.web.dto.user.UserReadDto;


public record CourseInfoReadDto(
    Long id,
    UserReadDto user,
    CourseReadDto course,
    ProgressReadDto progress
) {
}
