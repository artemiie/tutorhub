package com.tutorhub.security.jwt.service.params;

import com.tutorhub.security.jwt.TokenType;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "hiddenBuilder", access = AccessLevel.PRIVATE)
@Getter
public class TokenParameters {

  private Map<String, Object> fields;
  private TokenType type;
  private String subject;
  private Date issuedAt;
  private Date expiredAt;

  public static TokenParametersBuilder builder(final String subject, final Duration duration) {
    Date issuedAt = new Date();
    return hiddenBuilder()
        .fields(new HashMap<>())
        .issuedAt(issuedAt)
        .subject(subject)
        .expiredAt(new Date(issuedAt.getTime() + 1000 * duration.get(ChronoUnit.SECONDS)));
  }

  public static class TokenParametersBuilder {

    public TokenParametersBuilder field(final String field, final Object value) {
      this.fields.put(field, value);
      return this;
    }

    public TokenParametersBuilder fields(final Map<String, Object> fields) {
      this.fields = fields;
      return this;
    }

    public TokenParametersBuilder type(final TokenType type) {
      this.type = type;
      field("type", type);
      return this;
    }

    public TokenParametersBuilder issuedAt(final Date issuedAt) {
      this.issuedAt = issuedAt;
      field("issuedAt", issuedAt);
      return this;
    }

    public TokenParametersBuilder expiredAt(final Date expiredAt) {
      this.expiredAt = expiredAt;
      field("expiredAt", expiredAt);
      return this;
    }

    public TokenParametersBuilder subject(final String subject) {
      this.subject = subject;
      field("subject", subject);
      return this;
    }

    public TokenParameters build() {
      return new TokenParameters(fields, type, subject, issuedAt, expiredAt);
    }
  }
}
