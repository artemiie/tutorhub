package com.tutorhub.mail;

import com.tutorhub.mail.builder.ActivationEmailBuilder;
import com.tutorhub.mail.builder.EmailBuilder;
import com.tutorhub.mail.builder.LoginEmailBuilder;
import com.tutorhub.mail.builder.RestoreEmailBuilder;
import com.tutorhub.mail.model.MailBody;
import com.tutorhub.mail.model.MailInfo;
import com.tutorhub.mail.model.MailType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final EmailSender emailSender;
  private final LoginEmailBuilder loginEmailBuilder;
  private final RestoreEmailBuilder restoreEmailBuilder;
  private final ActivationEmailBuilder activationEmailBuilder;

  private final Map<MailType, EmailBuilder> emailBuilderByType =
      new HashMap<>();

  @PostConstruct
  protected void init() {
    emailBuilderByType.put(MailType.LOGIN, loginEmailBuilder);
    emailBuilderByType.put(MailType.RESTORE, restoreEmailBuilder);
    emailBuilderByType.put(MailType.ACTIVATION, activationEmailBuilder);
  }

  @SneakyThrows
  public void send(final MailInfo mailInfo) {
    MailType mailType = mailInfo.getMailType();
    EmailBuilder emailBuilder = emailBuilderByType.get(mailType);
    MailBody mailBody = emailBuilder.build(mailInfo);
    emailSender.send(
        mailInfo.getRecipientEmail(),
        mailBody.getSubject(),
        mailBody.getText());
  }
}
