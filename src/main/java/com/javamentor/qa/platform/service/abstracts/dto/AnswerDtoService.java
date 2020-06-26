package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;

import java.util.List;

public interface AnswerDtoService {
    List<AnswerDto> getAnswersDtoByQuestionIdSortNew(Long questionId, Long userId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId, Long userId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId, Long userId);
    Boolean isUserNotAnswered(Long questionId, Long userId);
}
