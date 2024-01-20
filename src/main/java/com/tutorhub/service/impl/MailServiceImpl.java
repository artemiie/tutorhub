package com.tutorhub.service.impl;

import com.tutorhub.model.MailType;
import com.tutorhub.model.User;
import com.tutorhub.service.MailService;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final Configuration configuration;
  private final JavaMailSender mailSender;

  @Override
  @SneakyThrows
  public void sendEmail(final User user, final MailType type, final Properties params) {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

    String emailContext =
        switch (type) {
          case REGISTRATION -> buildRegistrationEmailContext(helper, user, params);
          case LOGIN -> buildLoginEmailContext(helper, user, params);
          case RESTORE -> buildRestoreEmailContext(helper, user, params);
        };

    helper.setText(emailContext, true);
    helper.setTo(user.getUsername());
    mailSender.send(mimeMessage);
  }

  @SneakyThrows
  private String buildRegistrationEmailContext(
      final MimeMessageHelper helper,
      final User user,
      final Properties params
  ) {
    helper.setSubject("Thank you for registration, " + user.getFullName());
    return getRegistrationEmailContext(user, params);
  }

  @SneakyThrows
  private String buildLoginEmailContext(
      final MimeMessageHelper helper,
      final User user,
      final Properties params
  ) {
    helper.setSubject("Login Information");
    return getLoginEmailContext(user, params);
  }

  @SneakyThrows
  private String buildRestoreEmailContext(
      final MimeMessageHelper helper,
      final User user,
      final Properties params
  ) {
    helper.setSubject("Password Restoration");
    return getRestoreEmailContext(user, params);
  }

  @SneakyThrows
  private String getRegistrationEmailContext(final User user, final Properties properties) {
    StringWriter writer = new StringWriter();
    Map<String, Object> model = new HashMap<>();
    model.put("name", user.getFullName());
    model.put("token", properties.get("token"));
    configuration.getTemplate("register.ftlh").process(model, writer);
    return writer.getBuffer().toString();
  }

  // todo create field about loginTime
  @SneakyThrows
  private String getLoginEmailContext(final User user, final Properties properties) {
    StringWriter writer = new StringWriter();
    Map<String, Object> model = new HashMap<>();
    model.put("name", user.getFullName());
    configuration.getTemplate("login.ftlh").process(model, writer);
    return writer.getBuffer().toString();
  }

  @SneakyThrows
  private String getRestoreEmailContext(final User user, final Properties properties) {
    StringWriter writer = new StringWriter();
    Map<String, Object> model = new HashMap<>();
    model.put("username", properties.get("username"));
    model.put("token", properties.get("token"));
    configuration.getTemplate("restore.ftlh").process(model, writer);
    return writer.getBuffer().toString();
  }
}
