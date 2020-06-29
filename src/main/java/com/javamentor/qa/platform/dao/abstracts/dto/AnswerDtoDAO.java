package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;

public interface AnswerDtoDAO {
    List<AnswerDto> getAnswersDtoByQuestionIdSortNew(Long questionId, Long userId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId, Long userId);
    List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId, Long userId);
    Boolean isUserNotAnswered(Long questionId, Long userId);

//    new methods
    Long getAnswerCountByUserId(Long user_id);

    List<AnswerDto> getAnswerDtoByUserIdSortByDate(Long userId, Integer page);

    List<AnswerDto> getAnswerDtoByUserIdSortByViews(Long userId, Integer page);

    List<AnswerDto> getAnswerDtoByUserIdSortByVotes(Long userId, Integer page);
}
