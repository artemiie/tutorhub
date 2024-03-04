package com.tutorhub.web.controller;

import com.tutorhub.model.user.User;
import com.tutorhub.service.UserService;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants.Delete;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants.Find;
import com.tutorhub.web.controller.swagger.constants.ModuleApiConstants.Update;
import com.tutorhub.web.controller.swagger.constants.UserApiConstants;
import com.tutorhub.web.dto.UserDTO;
import com.tutorhub.web.dto.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = UserApiConstants.NAME, description = UserApiConstants.DESCRIPTION)
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @Operation(summary = Find.SUMMARY, description = Find.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Find.ResponseCode200.CODE,
          description = Find.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.ResponseCode404.CODE,
          description = Find.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = Find.ResponseCode500.CODE,
          description = Find.ResponseCode500.DESCRIPTION)
  })
  @GetMapping("/{userId}")
  public UserDTO find(@PathVariable final Long userId) {
    User studentEntity = userService.find(userId);
    return userMapper.toDto(studentEntity);
  }

  @Operation(summary = Update.SUMMARY, description = Update.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Update.ResponseCode200.CODE,
          description = Update.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Update.ResponseCode400.CODE,
          description = Update.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Update.ResponseCode403.CODE,
          description = Update.ResponseCode403.DESCRIPTION),
      @ApiResponse(
          responseCode = Update.ResponseCode404.CODE,
          description = Update.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = Update.ResponseCode500.CODE,
          description = Update.ResponseCode500.DESCRIPTION)
  })
  @PutMapping
  public UserDTO update(@Validated @RequestBody final UserDTO userDTO) {
    User user = userMapper.fromDto(userDTO);
    User updatedUser = userService.update(user);
    return userMapper.toDto(updatedUser);
  }

  @Operation(summary = Delete.SUMMARY, description = Delete.DESCRIPTION)
  @ApiResponses({
      @ApiResponse(
          responseCode = Delete.ResponseCode200.CODE,
          description = Delete.ResponseCode200.DESCRIPTION),
      @ApiResponse(
          responseCode = Delete.ResponseCode400.CODE,
          description = Delete.ResponseCode400.DESCRIPTION),
      @ApiResponse(
          responseCode = Delete.ResponseCode403.CODE,
          description = Delete.ResponseCode403.DESCRIPTION),
      @ApiResponse(
          responseCode = Delete.ResponseCode404.CODE,
          description = Delete.ResponseCode404.DESCRIPTION),
      @ApiResponse(
          responseCode = Delete.ResponseCode500.CODE,
          description = Delete.ResponseCode500.DESCRIPTION)
  })
  @DeleteMapping("/{userId}")
  public void delete(@PathVariable final Long userId) {
    userService.delete(userId);
  }
}
