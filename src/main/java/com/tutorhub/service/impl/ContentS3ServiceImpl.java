package com.tutorhub.service.impl;

import com.tutorhub.exception.InvalidFormatException;
import com.tutorhub.model.course.ContentType;
import com.tutorhub.s3.client.service.AwsS3Service;
import com.tutorhub.service.ContentS3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentS3ServiceImpl implements ContentS3Service {
  private final AwsS3Service awsS3Service;

  @Override
  public Object find(final String filename) {
    return awsS3Service.find(filename);
  }

  @Override
  @SneakyThrows
  public String upload(final MultipartFile multipartFile) {
    String contentType = multipartFile.getContentType();
    List<String> extensions = ContentType.allExtensions();

    if (!extensions.contains(contentType)) {
      throw new InvalidFormatException("Format not suported");
    }

    String fileId;

    do {
      fileId = UUID.randomUUID().toString();
    } while (awsS3Service.exists(fileId));

    File file = Files.createTempFile("temp", ".tmp").toFile();
    multipartFile.transferTo(file);

    awsS3Service.upload(fileId, file);

    return fileId;
  }

}
