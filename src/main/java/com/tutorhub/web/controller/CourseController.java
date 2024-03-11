package com.tutorhub.web.controller;

import com.tutorhub.model.course.Course;
import com.tutorhub.service.CourseService;
import com.tutorhub.web.controller.swagger.constants.CourseApiConstants;
import com.tutorhub.web.dto.course.CourseCreationDto;
import com.tutorhub.web.dto.course.CoursePagedDto;
import com.tutorhub.web.dto.course.CourseReadDto;
import com.tutorhub.web.dto.course.CourseUpdateDto;
import com.tutorhub.web.dto.mapper.CourseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RestController;

import static com.tutorhub.web.controller.swagger.constants.CourseApiConstants.Create;
import static com.tutorhub.web.controller.swagger.constants.CourseApiConstants.Delete;
import static com.tutorhub.web.controller.swagger.constants.CourseApiConstants.EnrollUser;
import static com.tutorhub.web.controller.swagger.constants.CourseApiConstants.Find;
import static com.tutorhub.web.controller.swagger.constants.CourseApiConstants.FindAllByUserPaged;
import static com.tutorhub.web.controller.swagger.constants.CourseApiConstants.Update;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
@Tag(name = CourseApiConstants.NAME,
    description = CourseApiConstants.DESCRIPTION)
public class CourseController {
  private final CourseService courseService;
  private final CourseMapper courseMapper;

  @Operation(summary = Find.SUMMARY, description = Find.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Find.ResponseCode200.CODE,
          description = Find.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.ResponseCode404.CODE,
          description = Find.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.ResponseCode500.CODE,
          description = Find.ResponseCode500.DESCRIPTION)
  })
  @GetMapping("/{courseId}")
  public CourseReadDto find(@PathVariable final Long courseId) {
    Course courseEntity = courseService.find(courseId);
    return courseMapper.toCourseReadDto(courseEntity);
  }

  @Operation(
      summary = FindAllByUserPaged.SUMMARY,
      description = FindAllByUserPaged.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = FindAllByUserPaged.ResponseCode200.CODE,
          description = FindAllByUserPaged.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = FindAllByUserPaged.ResponseCode404.CODE,
          description = FindAllByUserPaged.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = FindAllByUserPaged.ResponseCode500.CODE,
          description = FindAllByUserPaged.ResponseCode500.DESCRIPTION)
  })
  @GetMapping
  public Page<CourseReadDto> findAllByUserPaged(
      @RequestBody final CoursePagedDto coursePagedDto) {
    Page<Course> courses =
        courseService.findAllByUser(
            coursePagedDto.userId(),
            PageRequest.of(
                coursePagedDto.pageNumber(),
                coursePagedDto.pageSize(),
                Sort.by(coursePagedDto.sortBy())
            )
        );
    return courses.map(courseMapper::toCourseReadDto);
  }

  @Operation(summary = Create.SUMMARY, description = Create.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Create.ResponseCode200.CODE,
          description = Create.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Create.ResponseCode400.CODE,
          description = Create.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Create.ResponseCode500.CODE,
          description = Create.ResponseCode500.DESCRIPTION)
  })
  @PostMapping
  @PreAuthorize("@customSecurityExpresion.isCurrentUser(#courseDTO.userId)")
  public CourseReadDto create(
      @RequestBody @Validated final CourseCreationDto courseDTO) {
    Course entity = courseMapper.fromDto(courseDTO);

    Course createdEntity = courseService.create(entity);

    return courseMapper.toCourseReadDto(createdEntity);
  }

  @Operation(summary = Update.SUMMARY, description = Update.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Update.ResponseCode200.CODE,
          description = Update.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Update.ResponseCode400.CODE,
          description = Update.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Update.ResponseCode403.CODE,
          description = Update.ResponseCode403.DESCRIPTION),
      @ApiResponse(
          responseCode = Update.ResponseCode404.CODE,
          description = Update.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = Update.ResponseCode500.CODE,
          description = Update.ResponseCode500.DESCRIPTION)
  })
  @PutMapping
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseDTO.id)")
  public CourseReadDto update(
      @Validated @RequestBody final CourseUpdateDto courseDTO) {
    Course entity = courseMapper.fromDto(courseDTO);
    Course updated = courseService.update(entity);
    return courseMapper.toCourseReadDto(updated);
  }

  @Operation(summary = Delete.SUMMARY, description = Delete.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Delete.ResponseCode200.CODE,
          description = Delete.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Delete.ResponseCode400.CODE,
          description = Delete.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Delete.ResponseCode403.CODE,
          description = Delete.ResponseCode403.DESCRIPTION),
      @ApiResponse(
          responseCode = Delete.ResponseCode404.CODE,
          description = Delete.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = Delete.ResponseCode500.CODE,
          description = Delete.ResponseCode500.DESCRIPTION)
  })
  @DeleteMapping("/{courseId}")
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public void delete(@PathVariable final Long courseId) {
    courseService.delete(courseId);
  }

  @Operation(summary = EnrollUser.SUMMARY, description = EnrollUser.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = EnrollUser.ResponseCode200.CODE,
          description = EnrollUser.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = EnrollUser.ResponseCode400.CODE,
          description = EnrollUser.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = EnrollUser.ResponseCode403.CODE,
          description = EnrollUser.ResponseCode403.DESCRIPTION),
      @ApiResponse(
          responseCode = EnrollUser.ResponseCode404.CODE,
          description = EnrollUser.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = EnrollUser.ResponseCode500.CODE,
          description = EnrollUser.ResponseCode500.DESCRIPTION)
  })
  @PutMapping("/{courseId}")
  @PreAuthorize("@customSecurityExpresion.isCurrentUser(#userId)"
      + " && !@customSecurityExpresion.isCourseOwner(#courseId)")
  public void enrollUser(@RequestHeader("USER_ID") final Long userId,
                         @PathVariable final Long courseId) {
    courseService.assignUser(userId, courseId);
  }
}
