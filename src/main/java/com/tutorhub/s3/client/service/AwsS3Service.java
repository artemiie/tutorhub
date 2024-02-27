package com.tutorhub.s3.client.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
  private final String bucket = "tutorhub.storage";
  private final AmazonS3 s3;

  @SneakyThrows
  public Object find(String fileName) {
    S3Object object = s3.getObject(bucket, fileName);

    InputStream content = object.getObjectContent();

    return IOUtils.toByteArray(content);
  }
}
