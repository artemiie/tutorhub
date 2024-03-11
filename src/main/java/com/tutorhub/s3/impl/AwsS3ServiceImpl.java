package com.tutorhub.s3.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.amazonaws.util.IOUtils;
import com.tutorhub.exception.InvalidFormatException;
import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.ContentType;
import com.tutorhub.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3ServiceImpl implements AwsS3Service {
  private final String bucket = "tutorhub.storage";
  private final AmazonS3 s3;

  @Override
  @SneakyThrows
  public Object find(final String fileName) {
    S3Object s3Object;

    try {
      s3Object = s3.getObject(bucket, fileName);
    } catch (AmazonS3Exception s3Exception) {
      throw
          new ResourceNotFoundException(
              "File %s not found".formatted(fileName));
    }

    InputStream content = s3Object.getObjectContent();

    return IOUtils.toByteArray(content);
  }

  public boolean exists(final String filename) {
    return s3.doesObjectExist(bucket, filename);
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
    } while (exists(fileId));

    File file = Files.createTempFile("temp", ".tmp").toFile();
    multipartFile.transferTo(file);

    long maxPartSize = 5 * 1024 * 1024; // 5 MB
    long fileLength = file.length();

    if (fileLength <= maxPartSize) {
      s3.putObject(bucket, fileId, file);
    } else {

      InitiateMultipartUploadRequest initRequest =
          new InitiateMultipartUploadRequest(bucket, fileId);

      InitiateMultipartUploadResult initResponse =
          s3.initiateMultipartUpload(initRequest);

      long uploadedPartsLength = 0;
      List<PartETag> partETags = new ArrayList<>();

      for (int partNumber = 1; uploadedPartsLength < fileLength; partNumber++) {

        long partSize =
            Math.min(maxPartSize, (fileLength - uploadedPartsLength));

        UploadPartRequest uploadRequest =
            new UploadPartRequest()
                .withBucketName(bucket)
                .withKey(fileId)
                .withUploadId(initResponse.getUploadId())
                .withPartNumber(partNumber)
                .withPartSize(partSize)
                .withFileOffset(uploadedPartsLength)
                .withFile(file);

        UploadPartResult uploadResult = s3.uploadPart(uploadRequest);
        partETags.add(uploadResult.getPartETag());

        uploadedPartsLength += partSize;
      }
      CompleteMultipartUploadRequest compRequest =
          new CompleteMultipartUploadRequest(
              bucket, fileId, initResponse.getUploadId(), partETags);

      s3.completeMultipartUpload(compRequest);
    }

    return fileId;
  }
}
