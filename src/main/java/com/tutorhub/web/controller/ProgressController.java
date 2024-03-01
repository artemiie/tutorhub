package com.tutorhub.web.controller;

import com.tutorhub.service.ProgressService;
import com.tutorhub.web.dto.ProgressCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/progress")
public class ProgressController {
  private final ProgressService progressService;

  @PostMapping
  public void create(
      @RequestBody final ProgressCreationDto progressCreationDto) {
    progressService.create(
        progressCreationDto.userId(),
        progressCreationDto.courseId(),
        progressCreationDto.moduleId(),
        progressCreationDto.submoduleId());
  }
}
