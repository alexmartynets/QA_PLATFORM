package com.javamentor.qa.platform.dao.abstracrt.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface QuestionDaoDto {
    Optional<QuestionDto> getQuestionDtoByQuestionId(@NotNull Long questionId);
    List<QuestionDto> getListQuestionDtoByListQuestionId(List<Long> listQuestionId);
    List<Long> getListQuestionId();
}