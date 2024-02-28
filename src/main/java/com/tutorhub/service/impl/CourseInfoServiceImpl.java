package com.tutorhub.service.impl;

import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.repository.CourseInfoRepository;
import com.tutorhub.service.CourseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseInfoServiceImpl implements CourseInfoService {
  private final CourseInfoRepository courseInfoRepository;

  @Override
  public CourseInfo getById(final Long id) {
    return null;
  }

  @Override
  public Page<CourseInfo> getAll(final Pageable page) {
    return null;
  }

  @Override
  public CourseInfo create(final CourseInfo entity) {
    return courseInfoRepository.save(entity);
  }

  @Override
  public CourseInfo update(final CourseInfo entity) {
    return null;
  }

  @Override
  public boolean existsById(final Long id) {
    return false;
  }

  @Override
  public void delete(final Long id) {
  }
}
