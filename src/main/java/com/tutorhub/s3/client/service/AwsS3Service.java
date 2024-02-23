package com.tutorhub.s3.client.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.tutorhub.model.course.ContentType;
import jakarta.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
  private final String bucket = "tutorhub.storage";
  private final AmazonS3 s3;

  private final Map<ContentType, Function<InputStream, byte[]>> methodByFileType = new HashMap<>();

  @PostConstruct
  protected void init() {
    methodByFileType.put(ContentType.IMAGE, this::extractImage);
    methodByFileType.put(ContentType.VIDEO, this::extractVideo);
  }

  @SneakyThrows
  public Object findFile(String fileName, ContentType contentType) {
    S3Object object = s3.getObject(bucket, fileName);

    var function = methodByFileType.get(contentType);

    return function.apply(object.getObjectContent());
  }

  @SneakyThrows
  private byte[] extractImage(InputStream inputStream) {
    return IOUtils.toByteArray(inputStream);
  }

  @SneakyThrows
  private byte[] extractVideo(InputStream inputStream) {
    return IOUtils.toByteArray(inputStream);
  }
}
