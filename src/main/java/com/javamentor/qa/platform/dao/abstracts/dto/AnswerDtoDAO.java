package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;

import java.util.List;

public interface AnswerDtoDAO {
    List<AnswerDto> getAnswersDtoByQuestionIdSortNew(Long questionId, Long userId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId, Long userId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId, Long userId);
    Boolean isUserNotAnswered(Long questionId, Long userId);
}
