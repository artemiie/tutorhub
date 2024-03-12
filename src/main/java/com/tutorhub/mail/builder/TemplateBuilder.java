package com.tutorhub.mail.builder;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateBuilder {

  private final Configuration configuration;

  public String build(final String templateName,
                      final Map<String, String> model)
      throws IOException, TemplateException {
    StringWriter writer = new StringWriter();

    configuration.getTemplate(templateName).process(model, writer);

    return writer.getBuffer().toString();
  }
}
