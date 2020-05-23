package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.Question;

public interface QuestionDAO extends ReadWriteDAO<Question, Long> {

    int deleteById(Long id);

}
