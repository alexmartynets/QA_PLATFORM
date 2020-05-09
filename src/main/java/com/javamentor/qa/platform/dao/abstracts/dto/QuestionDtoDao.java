package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.UserDto;

import java.util.List;

public interface QuestionDtoDao {
    List<QuestionDto> getQuestionDtoList();
}