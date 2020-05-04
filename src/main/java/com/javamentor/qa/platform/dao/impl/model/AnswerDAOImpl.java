package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracrt.model.AnswerDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public abstract class AnswerDAOImpl<T, PK> extends AbstractDAOImpl<T, PK> implements AnswerDAO<T, PK> {


    @Override
    public List<T> getAnswersByQuestionID(PK questionId) {
        return entityManager
                .createQuery("select a from Answer a where a.question = :questionId", tClass)
                .setParameter("questionId", questionId)
                .getResultList();
    }
}
