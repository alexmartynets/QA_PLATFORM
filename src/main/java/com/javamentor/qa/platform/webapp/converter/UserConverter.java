package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public abstract class UserConverter {

    protected final PasswordEncoder passwordEncoder;

    protected UserConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Mappings({
            @Mapping (target = "role", source = "role", qualifiedByName = "roleSetter"),
            @Mapping (target = "imageUser", source = "imageUser", qualifiedByName = "toBlob"),
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(userDto.getPassword()))")}
            )
    public abstract User toEntity(UserDto userDto);

    @Mappings({
            @Mapping (target = "role", source = "role.name"),
            @Mapping(target = "imageUser", source = "imageUser", qualifiedByName = "toArray"),
            @Mapping(target = "password", ignore = true)}
    )
    public abstract UserDto toDto(User user);

    @Named("toBlob")
    protected Blob blob (byte[] imageUser) throws SQLException {
        return new SerialBlob(imageUser);
    }

    @Named("toArray")
    protected byte[] array (Blob imageUser) throws SQLException {
        return imageUser.getBytes(1, (int) imageUser.length());
    }

    @Named("roleSetter")
    protected Role roleSetter (String role){

        if (role.equals("") || role.equals("USER")){
            role = "USER";
            return Role.builder()
                    .id(2L)
                    .name(role)
                    .build();
        } else {
            return Role.builder()
                    .id(1L)
                    .name(role)
                    .build();
        }
    }
}