package com.tutorhub.mail.model;

public class MailInfoLogin extends MailInfo {
  public MailInfoLogin(final String recipientName,
                       final String recipientEmail) {
    this.mailType = MailType.LOGIN;

    this.recipientName = recipientName;
    this.recipientEmail = recipientEmail;
  }
}
