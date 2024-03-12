package com.tutorhub.s3;

import org.springframework.web.multipart.MultipartFile;

public interface AwsS3Service {
  Object find(String filename);

  boolean exists(String filename);

  String upload(MultipartFile file);
}
