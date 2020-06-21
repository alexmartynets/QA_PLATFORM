package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;

public interface AnswerDtoDAO {
    List<AnswerDto> getAnswersDtoByQuestionIdSortNew(Long questionId);

    List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId);

    List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId);

//    new methods
    List<AnswerDto> getAnswerDtoByUserId(Long user_id, String sort, int page);

    Long getAnswerCountByUserId(long user_id);
}
