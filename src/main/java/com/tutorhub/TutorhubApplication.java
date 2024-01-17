package com.tutorhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class TutorhubApplication {

  public static void main(final String[] args) {
    SpringApplication.run(TutorhubApplication.class, args);
  }
}
