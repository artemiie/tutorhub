package com.tutorhub.web.controller;

import com.tutorhub.model.course.Module;
import com.tutorhub.service.ModuleService;
import com.tutorhub.web.dto.ModuleDTO;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.mapper.ModuleMapper;
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
@RequestMapping("/api/v1/courses/{courseId}/modules")
public class ModuleController {
  private final ModuleService moduleService;
  private final ModuleMapper moduleMapper;

  @GetMapping("/{moduleId}")
  public ModuleDTO find(@PathVariable final Long courseId,
                        @PathVariable final Long moduleId) {
    Module moduleEntity = moduleService.find(courseId, moduleId);
    return moduleMapper.toDto(moduleEntity);
  }

  @GetMapping
  public Page<ModuleDTO> findAllPaged(
      @PathVariable final Long courseId,
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy) {
    Page<Module> modules =
        moduleService.findAllPaged(
            courseId, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
    return modules.map(moduleMapper::toDto);
  }

  @PostMapping
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public ModuleDTO create(
      @PathVariable final Long courseId,
      @RequestBody @Validated(OnCreate.class) final ModuleDTO moduleDTO) {
    Module entity = moduleMapper.fromDto(moduleDTO);
    Module savedModule = moduleService.create(courseId, entity);
    return moduleMapper.toDto(savedModule);
  }

  @PutMapping()
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public ModuleDTO update(@PathVariable final Long courseId,
                          @RequestBody final ModuleDTO moduleDTO) {
    Module updatedModule = moduleMapper.fromDto(moduleDTO);
    Module savedModule = moduleService.update(courseId, updatedModule);
    return moduleMapper.toDto(savedModule);
  }

  @DeleteMapping("/{moduleId}")
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public void delete(@PathVariable final Long courseId,
                     @PathVariable final Long moduleId) {
    moduleService.delete(courseId, moduleId);
  }
}
