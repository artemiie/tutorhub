package com.tutorhub.web.controller;

import com.tutorhub.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/content")
@RequiredArgsConstructor
public class ContentController {
  private final ContentService contentService;

  @GetMapping(
      path = "/{fileName}",
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public Object find(@PathVariable final String fileName) {
    return contentService.find(fileName);
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(@RequestParam(name = "file") final MultipartFile file) {
    return contentService.upload(file);
  }
}
