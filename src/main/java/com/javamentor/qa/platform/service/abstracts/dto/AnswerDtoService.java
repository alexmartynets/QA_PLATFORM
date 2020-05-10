package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;

import java.util.List;

public interface AnswerDtoService {
    List<AnswerDto> getAnswersDto(Long questionId);
    AnswerDto getAnswerDtoById(Long answerId);
}
