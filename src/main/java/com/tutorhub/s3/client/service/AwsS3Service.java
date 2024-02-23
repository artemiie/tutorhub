package com.tutorhub.s3.client.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.tutorhub.model.course.ContentType;
import jakarta.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.imageio.ImageIO;
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
    BufferedImage imageFromAWS = ImageIO.read(inputStream);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(imageFromAWS, "jpg", baos);

    return baos.toByteArray();
  }

  @SneakyThrows
  private byte[] extractVideo(InputStream inputStream) {
    File file = Files.createTempFile("file", ".tmp").toFile();

    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        fileOutputStream.write(buffer, 0, bytesRead);
      }
    }

    FileInputStream fl = new FileInputStream(file);
    byte[] arr = new byte[(int) file.length()];
    fl.read(arr);
    fl.close();
    return arr;
  }
}
