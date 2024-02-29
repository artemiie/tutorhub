package com.tutorhub.web.controller;

import com.tutorhub.model.course.Submodule;
import com.tutorhub.service.SubmoduleService;
import com.tutorhub.web.dto.OnCreate;
import com.tutorhub.web.dto.SubmoduleDTO;
import com.tutorhub.web.dto.mapper.SubmoduleMapper;
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
@RequestMapping("/api/v1/courses/{courseId}/modules/{moduleId}/submodule")
public class SubmoduleController {
  private final SubmoduleService submoduleService;
  private final SubmoduleMapper submoduleMapper;

  @GetMapping("/{submoduleId}")
  public SubmoduleDTO find(
      @PathVariable final Long courseId,
      @PathVariable final Long moduleId,
      @PathVariable final Long submoduleId) {
    Submodule submodule =
        submoduleService.find(courseId, moduleId, submoduleId);
    return submoduleMapper.toDto(submodule);
  }

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

  @DeleteMapping("/{submoduleId}")
  @PreAuthorize("@customSecurityExpresion.isCourseOwner(#courseId)")
  public void delete(@PathVariable final Long courseId,
                     @PathVariable final Long moduleId,
                     @PathVariable final Long submoduleId) {
    submoduleService.delete(courseId, moduleId, submoduleId);
  }
}
