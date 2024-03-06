package com.tutorhub.web.controller;

import com.tutorhub.service.ContentS3Service;
import com.tutorhub.web.controller.swagger.constants.ContentS3ApiConstants;
import com.tutorhub.web.controller.swagger.constants.ContentS3ApiConstants.Find;
import com.tutorhub.web.controller.swagger.constants.ContentS3ApiConstants.Upload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/awss3")
@Tag(
    name = ContentS3ApiConstants.NAME,
    description = ContentS3ApiConstants.DESCRIPTION)
public class ContentS3Controller {
  private final ContentS3Service contentS3Service;

  @Operation(summary = Find.SUMMARY, description = Find.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Find.RescponseCode200.CODE,
          description = Find.RescponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.RescponseCode400.CODE,
          description = Find.RescponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.RescponseCode500.CODE,
          description = Find.RescponseCode500.DESCRIPTION)
  })
  @GetMapping(
      path = "/{fileName}",
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public Object find(@PathVariable final String fileName) {
    return contentS3Service.find(fileName);
  }

  @Operation(summary = Upload.SUMMARY, description = Upload.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Upload.RescponseCode200.CODE,
          description = Upload.RescponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Upload.RescponseCode400.CODE,
          description = Upload.RescponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Upload.RescponseCode500.CODE,
          description = Upload.RescponseCode500.DESCRIPTION)
  })
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(@RequestParam(name = "file") final MultipartFile file) {
    return contentS3Service.upload(file);
  }
}
