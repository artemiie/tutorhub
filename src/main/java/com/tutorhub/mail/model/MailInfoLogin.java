package com.tutorhub.mail.model;

public class MailInfoLogin extends MailInfo {
  public MailInfoLogin(String recipientName, String recipientEmail) {
    this.mailType = MailType.LOGIN;

    this.recipientName = recipientName;
    this.recipientEmail = recipientEmail;
  }
}
