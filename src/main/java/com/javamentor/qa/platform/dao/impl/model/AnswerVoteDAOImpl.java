package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerVoteDAO;
import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AnswerVoteDAOImpl extends ReadWriteDAOImpl<AnswerVote, Long> implements AnswerVoteDAO {

    @Transactional
    @Override
    public List<AnswerVote> getAllVotesByAnswerId(Long answerId) {
        return entityManager
                .createQuery("select v from AnswerVote v where v.voteAnswerPK.answer.id = :answerId", AnswerVote.class)
                .setParameter("answerId", answerId)
                .getResultList();
    }

    @Transactional
    @Override
    public List<AnswerVote> getVotesOfUserByAnswer(Long answerId, Long userId) {
        return entityManager
                .createQuery("select v from AnswerVote v where v.voteAnswerPK.answer.id = :answerId and v.voteAnswerPK.user.id = :userId", AnswerVote.class)
                .setParameter("answerId", answerId)
                .setParameter("userId", userId)
                .getResultList();
    }
}
