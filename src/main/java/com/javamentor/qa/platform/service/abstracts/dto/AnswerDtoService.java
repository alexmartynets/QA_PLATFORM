package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;

import java.util.List;

public interface AnswerDtoService {
    List<AnswerDto> getAnswersDtoByQuestionIdSortNew(Long questionId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId);
    Boolean isUserAlreadyAnswered(Long questionId, Long userId);
}
