package com.tutorhub.service;

import org.springframework.web.multipart.MultipartFile;

public interface ContentService {
  Object find(String filename);

  String upload(MultipartFile file);
}
