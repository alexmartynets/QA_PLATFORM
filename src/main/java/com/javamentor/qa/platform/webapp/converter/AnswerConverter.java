package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
            @Mapping(target = "imageUser", expression = "java(toBlob(userDto.getImageUser()))"),
            @Mapping(target = "role.name", source = "role")
    })
    public abstract User dtoToUser(UserDto userDto);

    @Mappings({
            @Mapping(target = "imageUser", expression = "java(toByte(user.getImageUser()))"),
            @Mapping(target = "role", source = "role.name")
    })
    public abstract UserDto userToDto(User user);

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