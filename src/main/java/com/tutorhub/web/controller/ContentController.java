package com.tutorhub.web.controller;

import com.tutorhub.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/content")
@RequiredArgsConstructor
public class ContentController {
  private final ContentService contentService;

  @GetMapping(path = "/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public Object find(@PathVariable final String fileName) {
    return contentService.find(fileName);
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(@RequestParam(name = "file") MultipartFile file) {
    return contentService.upload(file);
  }
}
