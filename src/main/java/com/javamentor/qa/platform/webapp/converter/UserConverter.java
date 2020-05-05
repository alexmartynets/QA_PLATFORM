package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserConverter {
    @Mappings({
            @Mapping(target="role.name", source="role"),
    })
    public abstract User toEntity(UserDto userDto);
    @Mappings({
            @Mapping(target="role", source="user.role.name"),
    })
    public abstract UserDto toDto(User user);
    public abstract List<User> toEntityList(List<UserDto> entityList);
    public abstract List<UserDto> toDtoList(List<User> dtoList);
}