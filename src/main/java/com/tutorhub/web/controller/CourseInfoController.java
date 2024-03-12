package com.tutorhub.web.controller;

import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.model.course.Progress;
import com.tutorhub.service.CourseInfoService;
import com.tutorhub.service.ProgressService;
import com.tutorhub.web.controller.swagger.constants.CourseInfoApiConstants;
import com.tutorhub.web.controller.swagger.constants.CourseInfoApiConstants.Find;
import com.tutorhub.web.dto.courseinfo.CourseInfoReadDto;
import com.tutorhub.web.dto.mapper.CourseInfoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course-infos")
@Tag(
    name = CourseInfoApiConstants.NAME,
    description = CourseInfoApiConstants.DESCRIPTION)
public class CourseInfoController {
  private final CourseInfoService courseInfoService;
  private final CourseInfoMapper courseInfoMapper;
  private final ProgressService progressService;

  @Operation(summary = Find.SUMMARY, description = Find.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Find.ResponseCode200.CODE,
          description = Find.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.ResponseCode400.CODE,
          description = Find.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.ResponseCode500.CODE,
          description = Find.ResponseCode500.DESCRIPTION)
  })
  @GetMapping
  @PreAuthorize(
      "!@customSecurityExpresion.isCourseOwner(#courseId)"
          + " && @customSecurityExpresion.isCurrentUser(#userId)")
  public CourseInfoReadDto find(
      @RequestParam final Long userId,
      @RequestParam final Long courseId) {
    CourseInfo courseInfo =
        courseInfoService.findByUserIdAndCourseId(userId, courseId);

    List<Progress> progress = progressService.findByCourseInfo(courseInfo);

    return courseInfoMapper.toDto(courseInfo, progress);
  }
}
