package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import javax.sql.rowset.serial.SerialBlob;

import java.sql.Blob;
import java.sql.SQLException;


@Mapper(componentModel = "spring")
public abstract class AnswerConverter {

    @Mappings({
            @Mapping(target = "questionId", source = "question.id"),
            @Mapping(target = "userDto", source = "user")
    })
    public abstract AnswerDto answerToDto(Answer answer);

    @Mappings({
            @Mapping(target = "question.id", source = "questionId"),
            @Mapping(target = "user", source = "userDto"),
            @Mapping(target = "persistDateTime", constant = "2000-05-10T21:08:06")
    })
    public abstract Answer dtoToAnswer(AnswerDto answerDto);

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
    protected byte[] toByte(Blob blob) {
        try {
            if (blob != null)
                return blob.getBytes(1, (int) blob.length());
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Named("toBlob")
    protected Blob toBlob(byte[] bytes) {
        try {
            if (bytes != null)
                return new SerialBlob(bytes);
        } catch (SQLException e) {
            return null;
        }
        return null;
    }
}