package com.tutorhub.service;

import com.tutorhub.model.MailType;
import com.tutorhub.model.User;

import java.time.LocalDateTime;
import java.util.Properties;

public interface MailService {

    void sendEmail(User user, MailType type, Properties params);

}
