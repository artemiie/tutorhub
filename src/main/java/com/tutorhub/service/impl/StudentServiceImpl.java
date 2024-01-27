package com.tutorhub.service.impl;

import com.tutorhub.model.Student;
import com.tutorhub.model.exception.ResourceNotFoundException;
import com.tutorhub.repository.StudentRepository;
import com.tutorhub.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    return studentRepository.findAll(page);
  }

  @Override
  public Student update(final Student entity) {
    Student studentOnDb =
        studentRepository
            .findById(entity.getId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Student with username[" + entity.getUsername() + "] not found."));

    entity.setId(studentOnDb.getId());
    entity.setUsername(studentOnDb.getUsername());
    entity.setRole(studentOnDb.getRole());

    return studentRepository.save(entity);
  }

  @Override
  public boolean existsById(final ObjectId id) {
    return studentRepository.existsById(id);
  }

  @Override
  public void delete(final ObjectId id) {
    studentRepository.deleteById(id);
  }
}
