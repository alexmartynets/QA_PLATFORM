package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracrt.model.AnswerDAO;

import java.util.List;

public abstract class AnswerDAOImpl<T, PK> extends AbstractDAOImpl<T, PK> implements AnswerDAO<T, PK> {

    @Override
    public List<T> getAnswersByQuestionID(PK questionId) {
        return entityManager
                .createQuery("select a from Answer a where a.question = :questionId", tClass)
                .setParameter("questionId", questionId)
                .getResultList();
    }
}
