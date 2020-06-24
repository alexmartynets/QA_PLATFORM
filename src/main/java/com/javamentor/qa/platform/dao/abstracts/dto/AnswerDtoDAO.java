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
    Long getAnswerCountByUserId(long user_id);

    List<TagDto> getTagsFromAnswerByUserId(long user_id);

    List<AnswerDto> getAnswerDtoByUserIdSortByDate(Long user_id);

    List<AnswerDto> getAnswerDtoByUserIdSortByViews(Long user_id);

    List<AnswerDto> getAnswerDtoByUserIdSortByVotes(Long user_id);
}
