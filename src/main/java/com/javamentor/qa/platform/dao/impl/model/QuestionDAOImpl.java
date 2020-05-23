package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionDAO;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class QuestionDAOImpl extends ReadWriteDAOImpl<Question, Long> implements QuestionDAO {

    @Override
    @Transactional
    public int deleteById(Long id) {
        return entityManager.createQuery("DELETE FROM Question q WHERE q.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
