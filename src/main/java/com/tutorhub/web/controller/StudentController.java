package com.tutorhub.web.controller;

import com.tutorhub.service.StudentService;
import com.tutorhub.web.dto.StudentDTO;
import com.tutorhub.web.dto.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
