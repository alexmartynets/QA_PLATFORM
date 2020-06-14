package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public interface QuestionDtoService {

    List<QuestionDto> getAllQuestionDto();

    Optional<QuestionDto> getQuestionDtoById(Long id);

    List<QuestionDto> getQuestionDtoListByUserId(Long userId);

    Optional<QuestionDto> hasQuestionAnswer(Long questionId);

    Optional<QuestionDto> toUpdateQuestionDtoTitleOrDescription(QuestionDto questionDtoFromClient);

    Optional<QuestionDto> toVoteForQuestion(Long id, int vote);

    Pair<Long, List<QuestionDto>> getPaginationQuestion(int page, int size);
}
