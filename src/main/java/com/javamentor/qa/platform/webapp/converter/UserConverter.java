package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserConverter {
    public abstract User toUser(UserDto userDto);
    public abstract UserDto toUserDto(User user);
    public abstract List<UserDto> toUserDtos(List<User> users);
}