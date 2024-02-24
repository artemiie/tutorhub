package com.tutorhub.web.controller;

import com.tutorhub.model.course.Course;
import com.tutorhub.model.course.Module;
import com.tutorhub.service.CourseService;
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
@RequestMapping("/api/v1/modules")
public class ModuleController {
  private final ModuleService moduleService;
  private final ModuleMapper moduleMapper;
  private final CourseService courseService;

  @GetMapping("/{id}")
  public ModuleDTO find(@PathVariable final Long id) {
    Module moduleEntity = moduleService.getById(id);
    return moduleMapper.toDto(moduleEntity);
  }

  @GetMapping
  public Page<ModuleDTO> findAllPaged(
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy) {
    Page<Module> modules =
        moduleService.getAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
    return modules.map(moduleMapper::toDto);
  }

  @PostMapping
  @PreAuthorize("@customSecurityExpresion.canAccessCourse(#courseId)")
  public ModuleDTO create(
      @RequestParam(name = "courseId") Long courseId,
      @RequestBody @Validated(OnCreate.class) final ModuleDTO moduleDTO) {
    Course course = courseService.getById(courseId);
    Module entity = moduleMapper.fromDto(moduleDTO);
    entity.setCourse(course);
    Module savedModule = moduleService.create(entity);
    return moduleMapper.toDto(savedModule);
  }

  @PutMapping("/{courseId}/{moduleId}")
  @PreAuthorize("@customSecurityExpresion.canAccessCourse(#courseId)")
  public ModuleDTO update(
      @PathVariable(name = "courseId") Long courseId,
      @PathVariable(name = "moduleId") Long moduleId,
      @RequestBody final ModuleDTO moduleDTO) {

    Module existingModule = moduleService.getById(moduleId);
    Module updatedModule = moduleMapper.fromDto(moduleDTO);
    updatedModule.setId(existingModule.getId());
    updatedModule.setCourse(existingModule.getCourse());
    updatedModule.setContent(existingModule.getContent());
    Module updated = moduleService.update(updatedModule);
    return moduleMapper.toDto(updated);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("@customSecurityExpresion.canAccessCourse(#id)")
  public void delete(@PathVariable final Long id) {
    moduleService.delete(id);
  }
}
