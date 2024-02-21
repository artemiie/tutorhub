package com.tutorhub.mail.builder;

import com.tutorhub.mail.model.MailBody;
import com.tutorhub.mail.model.MailInfo;
import com.tutorhub.mail.model.MailInfoRestore;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestoreEmailBuilder implements EmailBuilder {

  public static final String RESTORE_SUBJECT = "Restore";
  public static final String RESTORE_TEMPLATE = "restore.ftlh";

  private final TemplateBuilder templateBuilder;

  @Override
  @SneakyThrows
  public MailBody build(MailInfo mailInfo) {
    MailInfoRestore mailInfoRestore = (MailInfoRestore) mailInfo;

    Map<String, String> dataModel = new HashMap<>();
    dataModel.put("recipientName", mailInfoRestore.getRecipientName());
    dataModel.put("restoreToken", mailInfoRestore.getRestoreToken());

    String mailText = templateBuilder.build(RESTORE_TEMPLATE, dataModel);

    return MailBody.builder().text(mailText).subject(RESTORE_SUBJECT).build();
  }
}
