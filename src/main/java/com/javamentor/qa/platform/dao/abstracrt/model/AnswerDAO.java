package com.javamentor.qa.platform.dao.abstracrt.model;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;

import java.util.List;

public interface AnswerDAO<T, PK> extends AbstractDAO<T, PK> {
    List<T> getAnswersByQuestionID(PK questionId);
}
