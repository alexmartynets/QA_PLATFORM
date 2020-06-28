package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public interface QuestionDtoService {

    List<QuestionDto> getAllQuestionDto();

    Optional<QuestionDto> getQuestionDtoById(Long id);

    Optional<QuestionDto> getQuestionDtoById(Long id, Long userId);

    List<QuestionDto> getQuestionDtoListByUserId(Long userId);

    Optional<QuestionDto> hasQuestionAnswer(Long questionId);

    Pair<Long, List<QuestionDto>> getPaginationQuestion(int page, int size);

    Integer getCountValuable(Long questionId);

    QuestionDto getCountValuableQuestionWithUserVote(Long questionId, Long userId);

    boolean isUserCanToVoteByQuestionUp(Long questionId, Long userId);

    boolean isUserCanToVoteByQuestionDown(Long questionId, Long userId);

    List<QuestionDto> getQuestionsByTagId(Long tagId);
}
