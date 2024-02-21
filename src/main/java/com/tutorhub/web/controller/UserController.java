package com.tutorhub.web.controller;

import com.tutorhub.model.User;
import com.tutorhub.service.UserService;
import com.tutorhub.web.dto.UserDTO;
import com.tutorhub.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping("/{id}")
  public UserDTO getById(@PathVariable final Long id) {
    User studentEntity = userService.getById(id);
    return userMapper.toDto(studentEntity);
  }

  @GetMapping()
  public Page<UserDTO> getAllPaged(
      @RequestParam(name = "page") final int pageNumber,
      @RequestParam(name = "size") final int pageSize,
      @RequestParam final String sortBy) {
    Page<User> usersPage =
        userService.getAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
    return usersPage.map(userMapper::toDto);
  }

  @PutMapping
  public UserDTO update(@Validated @RequestBody final UserDTO userDTO) {
    User studentEntity = userMapper.fromDto(userDTO);
    User updatedEntity = userService.update(studentEntity);
    return userMapper.toDto(updatedEntity);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable final Long id) {
    userService.delete(id);
  }
}
