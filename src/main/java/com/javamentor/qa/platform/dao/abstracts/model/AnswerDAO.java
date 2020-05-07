package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;

import java.util.List;

public interface AnswerDAO<T, PK> extends ReadWriteDao<T, PK> {
    List<T> getAnswersByQuestionID(PK questionId);
}
