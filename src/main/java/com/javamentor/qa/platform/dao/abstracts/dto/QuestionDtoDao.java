package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;

import java.util.List;
import java.util.Map;

public interface QuestionDtoDao {
    List<QuestionDto> getQuestionDtoList();

    Map<Long, List<QuestionDto>> getPaginationQuestion(int page, int size);
}