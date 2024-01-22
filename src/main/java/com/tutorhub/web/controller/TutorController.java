package com.tutorhub.web.controller;

import com.tutorhub.model.Tutor;
import com.tutorhub.service.TutorService;
import com.tutorhub.web.dto.TutorDTO;
import com.tutorhub.web.dto.mapper.TutorMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/api/v1/tutors")
public class TutorController {

  private final TutorMapper tutorMapper;
  private final TutorService tutorService;

  @GetMapping()
  public Page<TutorDTO> getAllPaged(
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy) {
    return tutorService
        .getAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)))
        .map(tutor -> tutorMapper.toDto(tutor));
  }

  @PutMapping
  public TutorDTO update(@Validated @RequestBody final TutorDTO tutorDTO) {
    return tutorMapper.toDto(tutorService.update(tutorMapper.fromDto(tutorDTO)));
  }

  @GetMapping("/{id}")
  public TutorDTO getById(@PathVariable final ObjectId id) {
    return tutorMapper.toDto(tutorService.getById(id));
  }

  @PostMapping
  public TutorDTO create(@RequestBody @Validated final TutorDTO tutorDTO) {
    Tutor newTutor = tutorMapper.fromDto(tutorDTO);
    Tutor createdTutot = tutorService.create(newTutor);
    return tutorMapper.toDto(createdTutot);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable final ObjectId id){
    tutorService.delete(id);
  }
}
