package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;

import java.util.List;
import java.util.Map;

public interface QuestionDtoDao {
    List<QuestionDto> getQuestionDtoList();

    List<QuestionDto> getPaginationQuestion(int page, int size);

    Long getCount();

    List<TagDto> getTag(long q_id);
}