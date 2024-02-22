package com.tutorhub.mail;

import com.tutorhub.mail.model.MailInfo;

public interface MailService {

  void send(MailInfo mailInfo);
}
