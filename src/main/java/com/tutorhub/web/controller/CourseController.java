package com.tutorhub.web.controller;

import com.tutorhub.model.Course;
import com.tutorhub.model.User;
import com.tutorhub.service.CourseService;
import com.tutorhub.service.impl.SecurityService;
import com.tutorhub.web.dto.CourseDTO;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
  private final CourseService courseService;
  private final CourseMapper courseMapper;
  private final SecurityService securityService;

  @GetMapping("/{id}")
  public CourseDTO findById(@PathVariable final Long id) {
    Course courseEntity = courseService.getById(id);
    return courseMapper.toDto(courseEntity);
  }

  @GetMapping
  public Page<CourseDTO> findAllPaged(
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy) {
    return courseService
        .getAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)))
        .map(course -> courseMapper.toDto(course));
  }

  @PostMapping
  @PreAuthorize("hasRole('TUTOR')")
  public CourseDTO create(@RequestBody @Validated(OnCreate.class) final CourseDTO courseDTO) {
    User currentLoggedInUser = securityService.getCurrentLoggedUser();

    Course entity = courseMapper.fromDto(courseDTO);
    entity.setCourseOwner(currentLoggedInUser);

    Course createdEntity = courseService.create(entity);

    return courseMapper.toDto(createdEntity);
  }

  @PutMapping
  @PreAuthorize("hasRole('TUTOR')")
  public CourseDTO update(@Validated @RequestBody final CourseDTO courseDTO) {
    Course entity = courseMapper.fromDto(courseDTO);
    Course updated = courseService.update(entity);
    return courseMapper.toDto(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable final Long id) {
    courseService.delete(id);
  }
}
