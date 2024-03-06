package com.tutorhub.web.controller;

import com.tutorhub.service.ProgressService;
import com.tutorhub.web.controller.swagger.constants.ProgressApiConstants;
import com.tutorhub.web.controller.swagger.constants.ProgressApiConstants.Create;
import com.tutorhub.web.dto.progress.ProgressCreationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(
    name = ProgressApiConstants.NAME,
    description = ProgressApiConstants.DESCRIPTION)
@RequestMapping("/api/v1/progress")
public class ProgressController {
  private final ProgressService progressService;

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
  @PreAuthorize(
      "@customSecurityExpresion.isCourseOwner(#progressDto.courseId)"
          + " && @customSecurityExpresion.isCurrentUser(#progressDto.userId)")
  public void create(
      @RequestBody final ProgressCreationDto progressDto) {
    progressService.create(
        progressDto.userId(),
        progressDto.courseId(),
        progressDto.moduleId(),
        progressDto.submoduleId());
  }
}
