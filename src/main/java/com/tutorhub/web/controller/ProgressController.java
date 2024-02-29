package com.tutorhub.web.controller;

import com.tutorhub.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/progress")
public class ProgressController {
  private final ProgressService progressService;

  @PutMapping
  public void update(
      @RequestParam final Long userId,
      @RequestParam final Long courseId,
      @RequestParam final Long moduleId,
      @RequestParam final Long submoduleId) {
    progressService.create(userId, courseId, moduleId, submoduleId);
  }
}
