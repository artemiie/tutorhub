package com.tutorhub.web.controller;

import com.tutorhub.model.course.Submodule;
import com.tutorhub.service.SubmoduleService;
import com.tutorhub.web.controller.swagger.constants.SubmoduleApiConstants;
import com.tutorhub.web.controller.swagger.constants.SubmoduleApiConstants.Create;
import com.tutorhub.web.controller.swagger.constants.SubmoduleApiConstants.Delete;
import com.tutorhub.web.controller.swagger.constants.SubmoduleApiConstants.Find;
import com.tutorhub.web.controller.swagger.constants.SubmoduleApiConstants.FindAllPaged;
import com.tutorhub.web.controller.swagger.constants.SubmoduleApiConstants.Update;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.SubmoduleDTO;
import com.tutorhub.web.dto.mapper.SubmoduleMapper;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(
    name = SubmoduleApiConstants.NAME,
    description = SubmoduleApiConstants.DESCRIPTION)
@RequestMapping("/api/v1/courses/{courseId}/modules/{moduleId}/submodule")
public class SubmoduleController {
  private final SubmoduleService submoduleService;
  private final SubmoduleMapper submoduleMapper;

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
  @GetMapping("/{submoduleId}")
  public SubmoduleDTO find(
      @PathVariable final Long courseId,
      @PathVariable final Long moduleId,
      @PathVariable final Long submoduleId) {
    Submodule submodule =
        submoduleService.find(courseId, moduleId, submoduleId);
    return submoduleMapper.toDto(submodule);
  }

  @Operation(
      summary = FindAllPaged.SUMMARY,
      description = FindAllPaged.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = FindAllPaged.ResponseCode200.CODE,
          description = FindAllPaged.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = FindAllPaged.ResponseCode404.CODE,
          description = FindAllPaged.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = FindAllPaged.ResponseCode500.CODE,
          description = FindAllPaged.ResponseCode500.DESCRIPTION)
  })
  @GetMapping
  public Page<SubmoduleDTO> findAllPaged(
      @PathVariable final Long courseId,
      @PathVariable final Long moduleId,
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy) {
    Page<Submodule> submodules =
        submoduleService.findAllPaged(
            courseId,
            moduleId,
            PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
    return submodules.map(submoduleMapper::toDto);
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
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public SubmoduleDTO create(
      @PathVariable final Long courseId,
      @PathVariable final Long moduleId,
      @RequestBody @Validated(OnCreate.class) final SubmoduleDTO submoduleDTO) {
    Submodule submodule = submoduleMapper.fromDto(submoduleDTO);
    Submodule savedSubodule =
        submoduleService.create(courseId, moduleId, submodule);
    return submoduleMapper.toDto(savedSubodule);
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
  @PutMapping()
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public SubmoduleDTO update(@PathVariable final Long courseId,
                             @PathVariable final Long moduleId,
                             @RequestBody final SubmoduleDTO submoduleDTO) {
    Submodule updatedSubmodule = submoduleMapper.fromDto(submoduleDTO);
    Submodule savedSubmodule = submoduleService.update(
        courseId, moduleId, updatedSubmodule);
    return submoduleMapper.toDto(savedSubmodule);
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
  @DeleteMapping("/{submoduleId}")
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public void delete(@PathVariable final Long courseId,
                     @PathVariable final Long moduleId,
                     @PathVariable final Long submoduleId) {
    submoduleService.delete(courseId, moduleId, submoduleId);
  }
}
