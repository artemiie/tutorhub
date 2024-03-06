package com.tutorhub.testfactory;

import com.tutorhub.model.course.Content;

public class ContentTestFactory {
  public static Content getContentTest(Long id) {
    Content content = new Content();
    content.setId(id);
    return content;
  }
}
