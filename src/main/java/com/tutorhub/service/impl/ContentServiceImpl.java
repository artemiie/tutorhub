package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Content;
import com.tutorhub.repository.ContentRepository;
import com.tutorhub.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
  private final ContentRepository contentRepository;

  @Override
  public Content find(final Long id) {
    return contentRepository
        .findById(id)
        .orElseThrow(() ->
            new ResourceNotFoundException(
                "Content with id[%s] not found.".formatted(id)));
  }

  @Override
  public Content create(final Content content) {
    return contentRepository.save(content);
  }
}
