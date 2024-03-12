package com.tutorhub.mail.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailInfoRestore extends MailInfo {
  private String restoreToken;

  public MailInfoRestore(final String recipientName,
                         final String recipientEmail,
                         final String restoreToken) {
    this.mailType = MailType.RESTORE;

    this.recipientName = recipientName;
    this.recipientEmail = recipientEmail;
    this.restoreToken = restoreToken;
  }
}
