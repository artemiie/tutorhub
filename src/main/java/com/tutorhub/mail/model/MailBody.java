package com.tutorhub.mail.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MailBody {
  private String subject;
  private String text;
}
