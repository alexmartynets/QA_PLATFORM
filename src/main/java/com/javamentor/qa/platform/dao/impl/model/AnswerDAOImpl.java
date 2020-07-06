package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDAO;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public class AnswerDAOImpl extends ReadWriteDAOImpl<Answer, Long> implements AnswerDAO {
    @Override
    @Transactional
    public Optional<Answer> getHelpfulAnswerByQuestionId(Long questionId) {

        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery(
                "select a from Answer a where a.question.id = :questionId and a.isHelpful = true", Answer.class)
                .setParameter("questionId", questionId));
    }
}
