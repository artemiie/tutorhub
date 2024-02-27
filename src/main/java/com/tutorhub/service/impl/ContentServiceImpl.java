package com.tutorhub.service.impl;

import com.tutorhub.s3.client.service.AwsS3Service;
import com.tutorhub.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
  private final AwsS3Service awsS3Service;

  @Override
  public Object find(String filename) {
    return awsS3Service.find(filename);
  }
}
