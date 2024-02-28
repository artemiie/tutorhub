package com.tutorhub.security.jwt.service.params;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Data
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

  private String secret;
  private Duration access;
  private Duration refresh;
  private Duration activation;
  private Duration restore;
}
