package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AnswerConverter {
    public abstract AnswerDto answerToAnswerDTO(Answer answer);

    public abstract Answer DTOToAnswer(AnswerDto answerDTO);

    public abstract List<AnswerDto> answersToDTOs (List<Answer> answers);
}
