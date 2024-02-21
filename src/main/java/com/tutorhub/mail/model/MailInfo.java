package com.tutorhub.mail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MailInfoLogin.class, name = "login"),
  @JsonSubTypes.Type(value = MailInfoActivation.class, name = "activation"),
  @JsonSubTypes.Type(value = MailInfoRestore.class, name = "restore")
})
public abstract class MailInfo implements Serializable {
  @JsonIgnore protected String type;
  protected MailType mailType;
  protected String recipientName;
  protected String recipientEmail;
}
