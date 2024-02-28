package com.tutorhub.mail.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailInfoActivation extends MailInfo {
  private String activationToken;

  public MailInfoActivation(final String recipientName,
                            final String recipientEmail,
                            final String activationToken) {
    this.mailType = MailType.ACTIVATION;

    this.recipientName = recipientName;
    this.recipientEmail = recipientEmail;
    this.activationToken = activationToken;
  }
}
