package com.tutorhub.service.impl;

import com.tutorhub.model.MailType;
import com.tutorhub.model.User;
import com.tutorhub.service.MailService;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Configuration configuration;
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(User user, MailType type, Properties params) {
        switch (type) {
            case REGISTRATION -> sendRegistrationEmail(user, params);
            case LOGIN -> sendLoginEmail(user, params);
            case RESTORE -> sendRestoreEmail(user, params);
            default -> {}
        }
    }

    @SneakyThrows
    private void sendRegistrationEmail(User user, Properties params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Thank you for registration, " + user.getFullName());
        String emailContext = getRegistrationEmailContext(user, params);
        helper.setText(emailContext, true);
        helper.setTo(user.getUsername());
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private void sendLoginEmail(User user, Properties params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Login Information");
        String emailContext = getLoginEmailContext(user, params);
        helper.setText(emailContext, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private void sendRestoreEmail(User user, Properties params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Password Restoration");
        String emailContext = getRestoreEmailContext(user, params);
        helper.setText(emailContext, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getRegistrationEmailContext(User user, Properties properties) {
        StringWriter writer = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getFullName());
        model.put("token", properties.get("token"));
        configuration.getTemplate("register.ftlh")
                .process(model, writer);
        return writer.getBuffer().toString();
    }

    // todo create field about loginTime
    @SneakyThrows
    private String getLoginEmailContext(User user, Properties properties) {
        StringWriter writer = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getFullName());
        configuration.getTemplate("login.ftlh")
                .process(model, writer);
        return  writer.getBuffer().toString();
    }

    @SneakyThrows
    private String getRestoreEmailContext(User user, Properties properties) {
        StringWriter writer = new StringWriter();
        //TODO reset password logic
        return writer.getBuffer().toString();
    }
}
