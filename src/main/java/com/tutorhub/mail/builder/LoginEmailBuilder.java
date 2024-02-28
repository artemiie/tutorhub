package com.tutorhub.mail.builder;

import com.tutorhub.mail.model.MailBody;
import com.tutorhub.mail.model.MailInfo;
import com.tutorhub.mail.model.MailInfoLogin;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginEmailBuilder implements EmailBuilder {
  public static final String LOGIN_SUBJECT = "Login";
  public static final String LOGIN_TEMPLATE = "login.ftlh";

  private final TemplateBuilder templateBuilder;

  @Override
  @SneakyThrows
  public MailBody build(final MailInfo mailInfo) {
    MailInfoLogin mailInfoLogin = (MailInfoLogin) mailInfo;
    Map<String, String> dataModel = new HashMap<>();
    dataModel.put("recipientName", mailInfoLogin.getRecipientName());

    String mailText = templateBuilder.build(LOGIN_TEMPLATE, dataModel);

    return MailBody.builder().text(mailText).subject(LOGIN_SUBJECT).build();
  }
}
