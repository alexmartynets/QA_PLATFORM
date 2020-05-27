package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;

import java.util.List;

public interface AnswerDtoDAO {
    List<AnswerDto> getAnswersDtoByQuestionId(Long questionId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId);
}
