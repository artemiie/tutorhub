package com.tutorhub.web.controller;

import com.tutorhub.model.course.Course;
import com.tutorhub.model.user.User;
import com.tutorhub.security.SecurityService;
import com.tutorhub.service.CourseService;
import com.tutorhub.web.dto.CourseDTO;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
  private final CourseService courseService;
  private final CourseMapper courseMapper;
  private final SecurityService securityService;

  @GetMapping("/{courseId}")
  public CourseDTO find(@PathVariable final Long courseId) {
    Course courseEntity = courseService.find(courseId);
    return courseMapper.toDto(courseEntity);
  }

  @GetMapping
  public Page<CourseDTO> findAllByUserPaged(
      @RequestParam final Long userId,
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy) {
    Page<Course> courses =
        courseService.findAllByUser(
            userId,
            PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
    return courses.map(courseMapper::toDto);
  }

  @PostMapping
  public CourseDTO create(
      @RequestBody @Validated(OnCreate.class) final CourseDTO courseDTO) {
    User currentLoggedInUser = securityService.getCurrentLoggedUser();

    Course entity = courseMapper.fromDto(courseDTO);
    entity.setCourseOwner(currentLoggedInUser);

    Course createdEntity = courseService.create(entity);

    return courseMapper.toDto(createdEntity);
  }

  @PutMapping
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseDTO.id)")
  public CourseDTO update(@Validated @RequestBody final CourseDTO courseDTO) {
    Course entity = courseMapper.fromDto(courseDTO);
    Course updated = courseService.update(entity);
    return courseMapper.toDto(updated);
  }

  @DeleteMapping("/{courseId}")
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public void delete(@PathVariable final Long courseId) {
    courseService.delete(courseId);
  }

  @PutMapping("/{courseId}")
  @PreAuthorize("@customSecurityExpresion.isCurrentUser(#userId)"
      + " && !@customSecurityExpresion.isCourseOwner(#courseId)")
  public void assignUser(@RequestHeader("USER_ID") final Long userId,
                         @PathVariable final Long courseId) {
    courseService.assignUser(userId, courseId);
  }
}
