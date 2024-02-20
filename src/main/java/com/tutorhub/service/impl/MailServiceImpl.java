package com.tutorhub.service.impl;

import com.tutorhub.model.MailType;
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
  public void sendEmail(
      final MailType type,
      final Properties params) {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

    String emailContext =
        switch (type) {
          case REGISTRATION -> buildRegistrationEmailContext(helper, params);
          case LOGIN -> buildLoginEmailContext(helper, params);
          case RESTORE -> buildRestoreEmailContext(helper, params);
        };

    helper.setText(emailContext, true);
    helper.setTo(params.getProperty("username"));
    mailSender.send(mimeMessage);
  }

  @SneakyThrows
  private String buildRegistrationEmailContext(
      final MimeMessageHelper helper, final Properties params) {
    helper.setSubject("Thank you for registration, " + params.getProperty("fullName"));
    return getRegistrationEmailContext(params);
  }

  @SneakyThrows
  private String buildLoginEmailContext(
      final MimeMessageHelper helper, final Properties params) {
    helper.setSubject("Login Information");
    return getLoginEmailContext(params);
  }

  @SneakyThrows
  private String buildRestoreEmailContext(
      final MimeMessageHelper helper, final Properties params) {
    helper.setSubject("Password Restoration");
    return getRestoreEmailContext(params);
  }

  @SneakyThrows
  private String getRegistrationEmailContext(final Properties properties) {
    StringWriter writer = new StringWriter();
    Map<String, Object> model = new HashMap<>();
    model.put("name", properties.getProperty("fullName"));
    model.put("token", properties.getProperty("token"));
    configuration.getTemplate("register.ftlh").process(model, writer);
    return writer.getBuffer().toString();
  }

  // todo create field about loginTime
  @SneakyThrows
  private String getLoginEmailContext(final Properties properties) {
    StringWriter writer = new StringWriter();
    Map<String, Object> model = new HashMap<>();
    model.put("name", properties.getProperty("fullName"));
    configuration.getTemplate("login.ftlh").process(model, writer);
    return writer.getBuffer().toString();
  }

  @SneakyThrows
  private String getRestoreEmailContext(final Properties properties) {
    StringWriter writer = new StringWriter();
    Map<String, Object> model = new HashMap<>();
    model.put("username", properties.getProperty("username"));
    model.put("token", properties.getProperty("token"));
    configuration.getTemplate("restore.ftlh").process(model, writer);
    return writer.getBuffer().toString();
  }
}