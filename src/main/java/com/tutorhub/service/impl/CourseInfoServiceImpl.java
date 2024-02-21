package com.tutorhub.service.impl;

import com.tutorhub.model.CourseInfo;
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
  public CourseInfo getById(Long id) {
    return null;
  }

  @Override
  public Page<CourseInfo> getAll(Pageable page) {
    return null;
  }

  @Override
  public CourseInfo create(CourseInfo entity) {
    return courseInfoRepository.save(entity);
  }

  @Override
  public CourseInfo update(CourseInfo entity) {
    return null;
  }

  @Override
  public boolean existsById(Long id) {
    return false;
  }

  @Override
  public void delete(Long id) {}
}