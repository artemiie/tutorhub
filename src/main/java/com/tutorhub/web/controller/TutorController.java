package com.tutorhub.web.controller;

import com.tutorhub.service.TutorService;
import com.tutorhub.web.dto.TutorDTO;
import com.tutorhub.web.dto.mapper.TutorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tutors")
public class TutorController {

  private final TutorMapper tutorMapper;
  private final TutorService tutorService;

  @GetMapping()
  public Page<TutorDTO> getAllPaged(
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy
  ) {
    return tutorService
        .getAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)))
        .map(tutor -> tutorMapper.toDto(tutor));
  }
}
