package com.tutorhub.mail.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailInfoRestore extends MailInfo {
  private String restoreToken;

  public MailInfoRestore(String recipientName, String recipientEmail, String restoreToken) {
    this.mailType = MailType.RESTORE;

    this.recipientName = recipientName;
    this.recipientEmail = recipientEmail;
    this.restoreToken = restoreToken;
  }
}
