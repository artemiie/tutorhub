package com.tutorhub.service.impl;

import com.tutorhub.model.Student;
import com.tutorhub.model.exception.ResourceAlreadyExistsException;
import com.tutorhub.model.exception.ResourceNotFoundException;
import com.tutorhub.repository.StudentRepository;
import com.tutorhub.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student getById(final ObjectId id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id[" + id + "] not found."));
    }

    @Override
    public Page<Student> getAll(final Pageable page) {
        return null;
    }
    @Override
    public Student create(final Student entity) {
        boolean userExists = studentRepository.existsByUsername(entity.getUsername());
        if (userExists) {
            throw new ResourceAlreadyExistsException(
                    "User with username [" + entity.getUsername() + "] already exists.");
        }
        return studentRepository.save(entity);
    }

    @Override
    public Student update(final Student entity) {
        return null;
    }

    @Override
    public boolean existsById(final ObjectId id) {
        return false;
    }

    @Override
    public void delete(final ObjectId id) {

    }
}
