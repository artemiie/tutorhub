package com.tutorhub.mail.builder;

import com.tutorhub.mail.model.MailBody;
import com.tutorhub.mail.model.MailInfo;

public interface EmailBuilder {
  MailBody build(MailInfo mailInfo);
}
