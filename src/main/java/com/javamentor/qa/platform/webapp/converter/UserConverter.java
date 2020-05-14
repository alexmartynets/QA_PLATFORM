package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    Role adminRole = Role.builder()
            .name("ADMIN")
            .build();

    Role userRole = Role.builder()
            .name("USER")
            .build();

    @Mappings({
            @Mapping(target = "role.name", source = "role"),
    })
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        if ((userDto.getRole() == null || userDto.getRole().equals("USER"))) {
            user.setRole(userRole);
        } else {
            user.setRole(adminRole);
        }

        user.setId( userDto.getId() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( passwordEncoder.encode(userDto.getPassword()) );
        user.setFullName( userDto.getFullName() );

        return user;
    }

    @Mappings({
            @Mapping(target = "role", source = "user.role.name"),
            @Mapping(target = "password", ignore = true)
    })
    public abstract UserDto toDto(User user);
    public abstract List<User> toEntityList(List<UserDto> entityList);
    public abstract List<UserDto> toDtoList(List<User> dtoList);
}