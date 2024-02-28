package com.tutorhub.model.course;

import java.util.ArrayList;
import java.util.List;

public enum ContentType {
  TEXT(List.of("text/plain", "text/xml", "text/html")),
  IMAGE(List.of("image/bmp", "image/jpeg", "application/xml")),
  VIDEO(List.of("video/mp4", "video/quicktime"));
  public final List<String> extensions;

  ContentType(final List<String> extensions) {
    this.extensions = extensions;
  }

  public static List<String> allExtensions() {
    List<String> extensions = new ArrayList<>();

    for (ContentType contentType : ContentType.values()) {
      extensions.addAll(contentType.extensions);
    }

    return extensions;
  }
}
