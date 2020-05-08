package com.javamentor.qa.platform.dao.abstracrt.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;

import java.util.List;

public interface QuestionDaoDto {
    List<QuestionDto> getListQuestionDto();
    List<TagDto> listTags();
}