package com.tutorhub.service;

import com.tutorhub.model.course.Content;

public interface ContentService {

  Content create(Content content);

  Content find(Long id);

}
