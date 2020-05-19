package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public abstract class UserConverter {

    protected PasswordEncoder passwordEncoder;
    protected RoleService roleService;

    @Autowired
    protected void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    protected void setRoleService(RoleService roleService) {
        this.roleService = roleService;
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
        if(imageUser != null) {
            return new SerialBlob(imageUser);
        } else {
            return null;
        }
    }

    @Named("toArray")
    protected byte[] array (Blob imageUser) throws SQLException {
        if(imageUser != null) {
            return imageUser.getBytes(1, (int) imageUser.length());
        } else {
            return new byte[0];
        }
    }

    @Named("roleSetter")
    protected Role roleSetter (String role){
        if (role == null){
            role = "USER";
        }
        return roleService.getByRoleName(role).get();
    }
}