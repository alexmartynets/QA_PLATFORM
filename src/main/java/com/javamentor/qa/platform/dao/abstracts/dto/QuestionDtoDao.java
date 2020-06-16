package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;

import java.util.List;
import java.util.Optional;

public interface QuestionDtoDao {

    List<QuestionDto> getQuestionDtoList();

    Optional<QuestionDto> getQuestionDtoById(Long id);

    List<QuestionDto> getQuestionDtoListByUserId(Long userId);

    Optional<QuestionDto> hasQuestionAnswer(Long questionId);

    List<QuestionDto> getQuestionList(int page, int size);

    List<TagDto> getTagList(long q_id);

    Long getCount();
}