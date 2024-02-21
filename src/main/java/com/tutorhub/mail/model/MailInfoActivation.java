package com.tutorhub.mail.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailInfoActivation extends MailInfo {
  private String activationToken;

  public MailInfoActivation(String recipientName, String recipientEmail, String activationToken) {
    this.mailType = MailType.ACTIVATION;

    this.recipientName = recipientName;
    this.recipientEmail = recipientEmail;
    this.activationToken = activationToken;
  }
}
