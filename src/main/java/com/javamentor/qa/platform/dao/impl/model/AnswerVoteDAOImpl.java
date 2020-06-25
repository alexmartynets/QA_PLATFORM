package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerVoteDAO;
import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AnswerVoteDAOImpl extends ReadWriteDAOImpl<AnswerVote, Long> implements AnswerVoteDAO {

    @Transactional
    @Override
    public Integer getAllVotesByAnswerId(Long answerId) {
        Number sum = (Long) entityManager
                .createQuery("select sum (v.vote) from AnswerVote v where v.voteAnswerPK.answer.id = :answerId")
                .unwrap(Query.class)
                .setParameter("answerId", answerId)
                .getSingleResult();
        return sum == null ? 0 : sum.intValue();
    }

    @Transactional
    @Override
    public Integer getVotesOfUserByAnswer(Long answerId, Long userId) {
        Number sum = (Long) entityManager
                .createQuery("select sum (v.vote) from AnswerVote v where v.voteAnswerPK.answer.id = :answerId and v.voteAnswerPK.user.id = :userId")
                .unwrap(Query.class)
                .setParameter("answerId", answerId)
                .setParameter("userId", userId)
                .getSingleResult();
        return sum == null ? 0 : sum.intValue();
    }
}
