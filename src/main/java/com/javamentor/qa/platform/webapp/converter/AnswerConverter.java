package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AnswerConverter{

    @Mappings({
            @Mapping(target="questionId", source="question.id"),
//            @Mapping(target="userDto.id", source="user.id"),
//            @Mapping(target="userDto.fullName", source="user.fullName"),
//            @Mapping(target="userDto.imageUser", source="user.imageUser"),
//            @Mapping(target="userDto.reputationCount", source="user.reputationCount")
//
    })
    public abstract AnswerDto entityToDto(Answer answer);


//    @Mappings({
//            @Mapping(target="question.id", source="questionId"),
//            @Mapping(target="user.fullName", source="userDto.fullName"),
//            @Mapping(target="user.imageUser", source="userDto.imageUser"),
//            @Mapping(target="user.reputationCount", source="userDto.reputationCount"),
//            @Mapping(target="user.id", source="userDto.id")
//    })
    public abstract Answer dtoToEntity(AnswerDto answerDto);

//    @Mappings({
//            @Mapping(target="question.id", source="questionId"),
//            @Mapping(target="user.id", source="userDto.id")
//    })
//    public abstract Answer dtoToEntityAdd(AnswerDto answerDto);
//
//    @Mappings({
//            @Mapping(target="question.id", source="questionId"),
//            @Mapping(target="user.fullName", source="userDto.fullName"),
//            @Mapping(target="user.imageUser", source="userDto.imageUser"),
//            @Mapping(target="user.reputationCount", source="userDto.reputationCount"),
//            @Mapping(target="user.id", source="userDto.id")
//    })
//    public abstract Answer dtoToEntityUpdate(AnswerDto answerDto);



    public abstract List<AnswerDto> entitiesToDtos(List<Answer> answers);

    public abstract List<Answer> dtosToEntities(List<AnswerDto> answerDtos);
}
