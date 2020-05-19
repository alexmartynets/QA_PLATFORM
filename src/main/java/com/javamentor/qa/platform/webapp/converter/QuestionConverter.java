package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public abstract class QuestionConverter {

    @Mappings({
            @Mapping(target = "user", source = "userDto"),
    })
    public abstract Question toEntity(QuestionDto questionDto);

    @Mappings({
            @Mapping(target = "userDto", source = "user")
    })
    public abstract QuestionDto toDto(Question question);

    @Mappings({
            @Mapping(target = "imageUser", source = "imageUser", qualifiedByName = "toBlob"),
            @Mapping(target = "role.name", source = "role")
    })
    public abstract User dtoToUser(UserDto userDto);

    @Mappings({
            @Mapping(target = "imageUser", source = "imageUser", qualifiedByName = "toByte"),
            @Mapping(target = "role", source = "role.name")
    })
    public abstract UserDto userToDto(User user);

    @Named("toByte")
    protected byte[] toByte(Blob blob) throws SQLException {
        if (blob != null) {
            return blob.getBytes(1, (int) blob.length());
        }
        return null;
    }

    @Named("toBlob")
    protected Blob toBlob(byte[] bytes) throws SQLException {
        if (bytes != null) {
            return new SerialBlob(bytes);
        }
        return null;
    }
}
