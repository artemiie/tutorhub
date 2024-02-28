package com.tutorhub.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {
  private final JavaMailSender mailSender;

  public void send(final String to,
                   final String subject,
                   final String text) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();

    MimeMessageHelper mimeMessageHelper =
        new MimeMessageHelper(mimeMessage, false, "UTF-8");

    mimeMessageHelper.setTo(to);
    mimeMessageHelper.setSubject(subject);
    mimeMessageHelper.setText(text, true);

    mailSender.send(mimeMessage);
  }
}
