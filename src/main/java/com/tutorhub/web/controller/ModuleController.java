package com.tutorhub.web.controller;

import com.tutorhub.model.course.Module;
import com.tutorhub.service.ModuleService;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants.Create;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants.Delete;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants.Find;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants.FindAllPaged;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants.Update;
import com.tutorhub.web.dto.mapper.ModuleMapper;
import com.tutorhub.web.dto.module.ModuleCreateDto;
import com.tutorhub.web.dto.module.ModuleReadDto;
import com.tutorhub.web.dto.module.ModuleUpdateDto;
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
    name = ModuleApiConstants.NAME,
    description = ModuleApiConstants.DESCRIPTION)
@RequestMapping("/api/v1/courses/{courseId}/modules")
public class ModuleController {
  private final ModuleService moduleService;
  private final ModuleMapper moduleMapper;

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
  @GetMapping("/{moduleId}")
  public ModuleReadDto find(@PathVariable final Long courseId,
                            @PathVariable final Long moduleId) {
    Module moduleEntity = moduleService.find(courseId, moduleId);
    return moduleMapper.toDto(moduleEntity);
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
  public Page<ModuleReadDto> findAllPaged(
      @PathVariable final Long courseId,
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy) {
    Page<Module> modules =
        moduleService.findAllPaged(
            courseId, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
    return modules.map(moduleMapper::toDto);
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
  public ModuleReadDto create(
      @PathVariable final Long courseId,
      @RequestBody @Validated final ModuleCreateDto moduleDTO) {
    Module entity = moduleMapper.fromDto(moduleDTO);
    Module savedModule = moduleService.create(courseId, entity);
    return moduleMapper.toDto(savedModule);
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
  public ModuleReadDto update(@PathVariable final Long courseId,
                              @RequestBody final ModuleUpdateDto moduleDTO) {
    Module updatedModule = moduleMapper.fromDto(moduleDTO);
    Module savedModule = moduleService.update(courseId, updatedModule);
    return moduleMapper.toDto(savedModule);
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
  @DeleteMapping("/{moduleId}")
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public void delete(@PathVariable final Long courseId,
                     @PathVariable final Long moduleId) {
    moduleService.delete(courseId, moduleId);
  }
}
