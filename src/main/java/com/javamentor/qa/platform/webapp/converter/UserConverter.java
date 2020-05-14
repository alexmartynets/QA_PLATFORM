package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
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
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "imageUser")
    })
    public UserDto toDto(User user) throws Exception {

        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRole( user.getRole().toString() );
        userDto.setId( user.getId() );
        userDto.setFullName( user.getFullName() );
        userDto.setEmail( user.getEmail() );
        userDto.setImageUser( loadBlob(user) );
        userDto.setAbout( user.getAbout() );
        userDto.setCity( user.getCity() );
        userDto.setLinkSite( user.getLinkSite() );
        userDto.setLinkGitHub( user.getLinkGitHub() );
        userDto.setLinkVk( user.getLinkVk() );
        userDto.setReputationCount( user.getReputationCount() );
        userDto.setPersistDateTime( user.getPersistDateTime() );
        userDto.setLastUpdateDateTime( user.getLastUpdateDateTime() );

        return userDto;
    }

    public abstract List<User> toEntityList(List<UserDto> entityList);

    public abstract List<UserDto> toDtoList(List<User> dtoList);

    private synchronized byte[] loadBlob(User user) throws Exception {
        Blob clob = user.getImageUser();
        if (clob == null)   return null;
        ByteArrayOutputStream output=loadInputStream(clob.getBinaryStream());
        return output.toByteArray();
    }

    private ByteArrayOutputStream loadInputStream(InputStream input) throws Exception
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int available = -1;
        while ((available = input.read(data)) > -1)
        {
            output.write(data, 0, available);
        }
        return output;
    }
}