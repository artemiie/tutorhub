package com.tutorhub.mail;

import com.tutorhub.mail.builder.EmailBuilder;
import com.tutorhub.mail.model.MailBody;
import com.tutorhub.mail.model.MailInfo;
import com.tutorhub.mail.model.MailType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final EmailSender emailSender;

  private final Map<String, EmailBuilder> emailBuilderByType;

  @SneakyThrows
  public void send(final MailInfo mailInfo) {
    MailType mailType = mailInfo.getMailType();
    EmailBuilder emailBuilder = emailBuilderByType.get(mailType.name());
    MailBody mailBody = emailBuilder.build(mailInfo);
    emailSender.send(
        mailInfo.getRecipientEmail(),
        mailBody.getSubject(),
        mailBody.getText());
  }
}
