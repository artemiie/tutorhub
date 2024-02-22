package com.tutorhub.mail.builder;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemplateBuilder {

  private final Configuration configuration;

  public String build(String templateName, Map<String, String> model)
      throws IOException, TemplateException {
    StringWriter writer = new StringWriter();

    configuration.getTemplate(templateName).process(model, writer);

    return writer.getBuffer().toString();
  }
}
