package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;

import java.util.Optional;

public interface AnswerDAO extends ReadWriteDAO<Answer, Long> {
    Optional<Answer> getHelpfulAnswerByQuestionId(Long questionId);
}
