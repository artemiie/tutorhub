package com.tutorhub.web.controller;

import com.tutorhub.model.Student;
import com.tutorhub.service.StudentService;
import com.tutorhub.web.dto.StudentDTO;
import com.tutorhub.web.dto.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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

    @PostMapping
    public StudentDTO create(@RequestBody @Validated final StudentDTO studentDTO) {
        Student newStudent = studentMapper.fromDto(studentDTO);
        Student createdStudent = studentService.create(newStudent);
        return studentMapper.toDto(createdStudent);
    }

}
