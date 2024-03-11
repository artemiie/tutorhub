package com.tutorhub.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.tutorhub.s3.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsS3Configuration {
  private final AwsS3Properties awsS3Properties;

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials credentials =
        new BasicAWSCredentials(
            awsS3Properties.getAccessKey(),
            awsS3Properties.getSecretKey());

    return AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(Regions.fromName(awsS3Properties.getRegion()))
        .build();
  }
}
