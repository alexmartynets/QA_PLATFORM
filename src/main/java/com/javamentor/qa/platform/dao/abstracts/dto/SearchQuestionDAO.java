package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;

import java.util.List;
import java.util.Optional;

public interface SearchQuestionDAO {
    List<QuestionDto> getQuestionsByUserId(Long id);

    List<QuestionDto> getQuestionsSortedByVotes();

    List<QuestionDto> getQuestionsByNumberOfAnswers(Long numberOfAnswers);

    List<QuestionDto> getQuestionsByNumberOfVotes(Long numberOfVotes);

    List<QuestionDto> getQuestionsByFieldHelpfulFalse();

    List<QuestionDto> getQuestionsByFieldHelpfulTrue();

    Boolean getTagByName(String name);

    List<QuestionDto> getQuestionsByTag(String tag);

    List<QuestionDto> search(String text);

    Optional<QuestionDto> settersForCommonSearch(Long questionId);
}