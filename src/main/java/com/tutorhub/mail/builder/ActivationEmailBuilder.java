package com.tutorhub.mail.builder;

import com.tutorhub.mail.model.MailBody;
import com.tutorhub.mail.model.MailInfo;
import com.tutorhub.mail.model.MailInfoActivation;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivationEmailBuilder implements EmailBuilder {

  public static final String ACTIVATION_SUBJECT = "Activation";
  public static final String ACTIVATION_TEMPLATE = "activation.ftlh";

  private final TemplateBuilder templateBuilder;

  @Override
  @SneakyThrows
  public MailBody build(MailInfo mailInfo) {
    MailInfoActivation activation = (MailInfoActivation) mailInfo;

    Map<String, String> dataModel = new HashMap<>();
    dataModel.put("recipientName", activation.getRecipientName());
    dataModel.put("activationToken", activation.getActivationToken());

    String mailText = templateBuilder.build(ACTIVATION_TEMPLATE, dataModel);

    return MailBody.builder().text(mailText).subject(ACTIVATION_SUBJECT).build();
  }
}
