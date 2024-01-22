package com.tutorhub.web.controller;

import com.tutorhub.model.Student;
import com.tutorhub.service.StudentService;
import com.tutorhub.web.dto.StudentDTO;
import com.tutorhub.web.dto.TutorDTO;
import com.tutorhub.web.dto.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentMapper studentMapper;
    private final StudentService studentService;

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable final ObjectId id) {
        return studentMapper.toDto(studentService.getById(id));
    }

    @GetMapping()
    public Page<StudentDTO> getAllPaged(
            @RequestParam(name = "page") final int pageNumber,
            @RequestParam(name = "size") final int pageSize,
            @RequestParam final String sortBy) {
        return studentService
                .getAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)))
                .map(student -> studentMapper.toDto(student));
    }

    @PostMapping
    public StudentDTO create(@RequestBody @Validated final StudentDTO studentDTO) {
        Student newStudent = studentMapper.fromDto(studentDTO);
        Student createdStudent = studentService.create(newStudent);
        return studentMapper.toDto(createdStudent);
    }

    @PutMapping
    public StudentDTO update(@Validated @RequestBody final StudentDTO studentDTO) {
        return studentMapper.toDto(studentService.update(studentMapper.fromDto(studentDTO)));
    }

}
