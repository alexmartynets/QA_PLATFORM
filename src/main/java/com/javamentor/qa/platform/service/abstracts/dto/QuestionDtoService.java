package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;

import java.util.List;
import java.util.Optional;

public interface QuestionDtoService {

    List<QuestionDto> getAllQuestionDto();

    Optional<QuestionDto> getQuestionDtoById(Long id);

    List<QuestionDto> getQuestionDtoListByUserId (Long userId);
}
