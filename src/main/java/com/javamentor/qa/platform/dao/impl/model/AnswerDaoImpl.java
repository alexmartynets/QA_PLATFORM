package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDao;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AnswerDaoImpl extends ReadWriteDaoImpl<Answer, Long> implements AnswerDao {

    @Override
    @Transactional
    public void deleteById(Long answerId) {
        entityManager.createQuery("delete from Answer a where a.id = :answerId")
                .setParameter("answerId", answerId)
                .executeUpdate();
    }
}
