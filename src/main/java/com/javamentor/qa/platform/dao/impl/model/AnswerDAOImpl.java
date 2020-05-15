package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDAO;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AnswerDAOImpl extends ReadWriteDAOImpl<Answer, Long> implements AnswerDAO {

    @Override
    public List<Answer> getAnswersByQuestionID(Long questionId) {
        return entityManager
                .createQuery("select a from Answer a where a.question = :questionId", tClass)
                .setParameter("questionId", questionId)
                .getResultList();
    }
}
