package com.tutorhub.web.controller;

import com.tutorhub.model.Course;
import com.tutorhub.service.CourseService;
import com.tutorhub.web.dto.CourseDTO;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
  private final CourseService courseService;
  private final CourseMapper courseMapper;

  @PostMapping
  @PreAuthorize("hasRole('TUTOR')")
  public CourseDTO create(@RequestBody @Validated(OnCreate.class) final CourseDTO courseDTO) {
    Course courseEntity = courseMapper.fromDto(courseDTO);
    Course createdCourseEntity = courseService.create(courseEntity);
    return courseMapper.toDto(createdCourseEntity);
  }
}
