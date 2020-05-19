package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;

import java.util.List;

public interface AnswerDAO extends ReadWriteDAO<Answer, Long> {

    List<Answer> getAnswersByQuestionID(Long questionId);

}
