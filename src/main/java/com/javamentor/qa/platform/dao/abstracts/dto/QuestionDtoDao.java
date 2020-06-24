package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuestionDtoDao {

    List<QuestionDto> getQuestionDtoList();

    Optional<QuestionDto> getQuestionDtoById(Long id);

    Optional<QuestionDto> getQuestionDtoById(Long questionId, Long userId);

    List<QuestionDto> getQuestionDtoListByUserId(Long userId);

    Optional<QuestionDto> hasQuestionAnswer(Long questionId);

    List<QuestionDto> getQuestionList(int page, int size);

    List<TagDto> getTagList(long q_id);

    Long getCount();

    Integer getCountValuableWithFalse(Long questionId);

    Integer getCountValuableWithTrue(Long questionId);

    Integer getCountValuable(Long questionId);

    Optional<QuestionDto> getCountValuableQuestionWithUserVote(Long questionId, Long userId);

    Integer sumVotesUserByVote(Long questionId, Long userId);


//    new methods

    Long getQuestionCountByUserId(long user_id);

    List<TagDto> getTagsByUserId(long user_id);

    List<QuestionDto> getQuestionDtoByUserIdSortByDate(Long user_id);

    List<QuestionDto> getQuestionDtoByUserIdSortByVotes(Long user_id);

    List<QuestionDto> getQuestionDtoByUserIdSortByViews(Long user_id);

}