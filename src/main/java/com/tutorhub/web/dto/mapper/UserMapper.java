package com.tutorhub.web.dto.mapper;


import com.tutorhub.model.user.User;
import com.tutorhub.web.dto.user.UserCreateDto;
import com.tutorhub.web.dto.user.UserReadDto;
import com.tutorhub.web.dto.user.UserUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserReadDto toUserReadDto(User user);

  User fromDto(UserCreateDto userCreateDto);

  User fromDto(UserUpdateDto userUpdateDto);

}
