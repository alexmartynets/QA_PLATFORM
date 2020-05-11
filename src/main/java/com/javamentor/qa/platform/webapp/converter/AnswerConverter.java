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
            @Mapping(target="fullName", source="user.fullName"),
            @Mapping(target="imageUser", source="user.imageUser"),
            @Mapping(target="reputationCount", source="user.reputationCount"),
            @Mapping(target="userId", source="user.id")
    })
    public abstract AnswerDto entityToDto(Answer answer);


    @Mappings({
            @Mapping(target="question.id", source="questionId"),
            @Mapping(target="user.fullName", source="fullName"),
            @Mapping(target="user.imageUser", source="imageUser"),
            @Mapping(target="user.reputationCount", source="reputationCount"),
            @Mapping(target="user.id", source="userId")
    })
    public abstract Answer dtoToEntity(AnswerDto answerDto);

    @Mappings({
            @Mapping(target="question.id", source="questionId"),
            @Mapping(target="user.fullName", source="fullName"),
            @Mapping(target="user.imageUser", source="imageUser"),
            @Mapping(target="user.reputationCount", source="reputationCount"),
            @Mapping(target="user.id", source="userId")
    })
    public abstract Answer dtoToEntityAdd(AnswerDto answerDto);

    @Mappings({
            @Mapping(target="question.id", source="questionId"),
            @Mapping(target="user.fullName", source="fullName"),
            @Mapping(target="user.imageUser", source="imageUser"),
            @Mapping(target="user.reputationCount", source="reputationCount"),
            @Mapping(target="user.id", source="userId")
    })
    public abstract Answer dtoToEntityUpdate(AnswerDto answerDto);



    public abstract List<AnswerDto> entitiesToDtos(List<Answer> answers);

    public abstract List<Answer> dtosToEntities(List<AnswerDto> answerDtos);
}
