package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserConverter {
    @Mappings({
            @Mapping(target="role.name", source="role"),
            @Mapping(target = "imageUser", expression = "java(toBlob(userDto.getImageUser()))")
    })
    public abstract User toEntity(UserDto userDto);
    @Mappings({
            @Mapping(target="role", source="user.role.name"),
            @Mapping(target = "imageUser", expression = "java(toByte(user.getImageUser()))")
    })
    public abstract UserDto toDto(User user);
    public abstract List<User> toEntityList(List<UserDto> entityList);
    public abstract List<UserDto> toDtoList(List<User> dtoList);

    public static byte[] toByte(Blob blob) {
        try {
            if (blob != null)
                return blob.getBytes(1, (int) blob.length());
        } catch (SQLException throwables) {
            return null;
        }
        return null;
    }

    public static Blob toBlob(byte[] bytes) {
        try {
            if (bytes != null)
                return new SerialBlob(bytes);
        } catch (SQLException throwables) {
            return null;
        }
        return null;
    }

}