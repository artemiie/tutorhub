package com.tutorhub.service;

import org.springframework.web.multipart.MultipartFile;

public interface ContentS3Service {
  Object find(String filename);

  String upload(MultipartFile file);
}
