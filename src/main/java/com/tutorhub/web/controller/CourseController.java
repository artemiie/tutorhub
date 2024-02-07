package com.tutorhub.web.controller;

import com.tutorhub.model.Course;
import com.tutorhub.model.Tutor;
import com.tutorhub.service.CourseService;
import com.tutorhub.service.impl.SecurityService;
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
  private final SecurityService securityService;

  @PostMapping
  @PreAuthorize("hasRole('TUTOR')")
  public CourseDTO create(@RequestBody @Validated(OnCreate.class) final CourseDTO courseDTO) {
    Tutor currentLoggedInUser = (Tutor) securityService.getCurrentLoggedUser();

    Course entity = courseMapper.fromDto(courseDTO);
    entity.setTutor(currentLoggedInUser);

    Course createdEntity = courseService.create(entity);

    return courseMapper.toDto(createdEntity);
  }
}
