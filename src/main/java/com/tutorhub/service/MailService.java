package com.tutorhub.service;

import com.tutorhub.model.MailType;
import java.util.Properties;

public interface MailService {

  void sendEmail(MailType type, Properties params);
}
