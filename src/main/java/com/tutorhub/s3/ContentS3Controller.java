package com.tutorhub.s3;

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
  private final AwsS3Service awsS3Service;

  @Operation(summary = Find.SUMMARY, description = Find.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Find.ResponseCode200.CODE,
          description = Find.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.ResponseCode400.CODE,
          description = Find.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.ResponseCode500.CODE,
          description = Find.ResponseCode500.DESCRIPTION)
  })
  @GetMapping(
      path = "/{fileId}",
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public Object find(@PathVariable final String fileId) {
    return awsS3Service.find(fileId);
  }

  @Operation(summary = Upload.SUMMARY, description = Upload.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Upload.ResponseCode200.CODE,
          description = Upload.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Upload.ResponseCode400.CODE,
          description = Upload.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Upload.ResponseCode500.CODE,
          description = Upload.ResponseCode500.DESCRIPTION)
  })
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(@RequestParam(name = "file") final MultipartFile file) {
    return awsS3Service.upload(file);
  }
}
