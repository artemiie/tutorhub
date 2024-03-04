package com.tutorhub.web.controller;

import com.tutorhub.model.user.User;
import com.tutorhub.service.UserService;
import com.tutorhub.web.dto.UserDTO;
import com.tutorhub.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping("/{userId}")
  public UserDTO getById(@PathVariable final Long userId) {
    User studentEntity = userService.find(userId);
    return userMapper.toDto(studentEntity);
  }

  @PutMapping
  public UserDTO update(@Validated @RequestBody final UserDTO userDTO) {
    User user = userMapper.fromDto(userDTO);
    User updatedUser = userService.update(user);
    return userMapper.toDto(updatedUser);
  }

  @DeleteMapping("/{userId}")
  public void delete(@PathVariable final Long userId) {
    userService.delete(userId);
  }
}
