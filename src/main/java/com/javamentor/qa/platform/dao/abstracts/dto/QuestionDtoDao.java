package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;

import java.util.List;
import java.util.Optional;

public interface QuestionDtoDao {

    List<QuestionDto> getQuestionDtoList();

    Optional<QuestionDto> getQuestionDtoById(Long id);

    List<QuestionDto> getQuestionDtoListByUserId(Long userId);
}