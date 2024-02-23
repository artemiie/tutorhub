package com.tutorhub.s3.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "aws")
public class AwsS3Properties {
  private String accessKey;
  private String secretKey;
  private String region;
}
