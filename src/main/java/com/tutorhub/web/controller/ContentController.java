package com.tutorhub.web.controller;

import com.tutorhub.model.course.Content;
import com.tutorhub.service.ContentService;
import com.tutorhub.web.controller.swagger.constants.ContentApiConstants;
import com.tutorhub.web.controller.swagger.constants.ContentApiConstants.Find;
import com.tutorhub.web.controller.swagger.constants.SubmoduleApiConstants.Create;
import com.tutorhub.web.dto.content.ContentCreateDto;
import com.tutorhub.web.dto.content.ContentReadDto;
import com.tutorhub.web.dto.mapper.ContentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/content")
@Tag(
    name = ContentApiConstants.NAME,
    description = ContentApiConstants.DESCRIPTION)
public class ContentController {
  private final ContentService contentService;
  private final ContentMapper contentMapper;

  @Operation(
      summary = Create.SUMMARY,
      description = Create.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Create.ResponseCode200.CODE,
          description = Create.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Create.ResponseCode400.CODE,
          description = Create.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Create.ResponseCode500.CODE,
          description = Create.ResponseCode500.DESCRIPTION)
  })
  @PostMapping
  public ContentReadDto create(
      @RequestBody @Validated final ContentCreateDto createDto) {
    Content content = contentMapper.fromDto(createDto);
    Content savedContent = contentService.create(content);
    return contentMapper.toDto(savedContent);
  }

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
      path = "/{contentId}",
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public Content find(@PathVariable final Long contentId) {
    return contentService.find(contentId);
  }
}
