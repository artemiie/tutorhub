package com.tutorhub.s3.client.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.tutorhub.exception.ResourceNotFoundException;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
    S3Object s3Object;

    try {
      s3Object = s3.getObject(bucket, fileName);
    } catch (AmazonS3Exception s3Exception) {
      throw new ResourceNotFoundException("File %s not found".formatted(fileName));
    }

    InputStream content = s3Object.getObjectContent();

    return IOUtils.toByteArray(content);
  }

  public boolean exists(String filename) {
    return s3.doesObjectExist(bucket, filename);
  }

  public void upload(String fileName, File file) {

    long maxPartSize = 5 * 1024 * 1024; // 5 MB
    long fileLength = file.length();

    if (fileLength <= maxPartSize) {
      s3.putObject(bucket, fileName, file);
    } else {

      InitiateMultipartUploadRequest initRequest =
          new InitiateMultipartUploadRequest(bucket, fileName);

      InitiateMultipartUploadResult initResponse = s3.initiateMultipartUpload(initRequest);

      long uploadedPartsLength = 0;
      List<PartETag> partETags = new ArrayList<>();

      for (int partNumber = 1; uploadedPartsLength < fileLength; partNumber++) {

        long partSize = Math.min(maxPartSize, (fileLength - uploadedPartsLength));

        UploadPartRequest uploadRequest =
            new UploadPartRequest()
                .withBucketName(bucket)
                .withKey(fileName)
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
              bucket, fileName, initResponse.getUploadId(), partETags);

      s3.completeMultipartUpload(compRequest);
    }
  }
}
