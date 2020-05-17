package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;

import java.util.List;

public interface AnswerDtoDao {
    List<AnswerDto> getAnswersDtoByQuestionId(Long questionId);
    AnswerDto getAnswerDtoById(Long answerId);
}
