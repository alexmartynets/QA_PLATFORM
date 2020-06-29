package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerVoteDAO;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class AnswerVoteDAOImpl extends ReadWriteDAOImpl<AnswerVote, Long> implements AnswerVoteDAO {

    @Transactional
    @Override
    public Integer getAllVotesByAnswerId(Long answerId) {
        Optional<Number> sum = SingleResultUtil.getSingleResultOrNull(entityManager
                .createQuery("select sum (v.vote) from AnswerVote v where v.voteAnswerPK.answer.id = :answerId", Number.class)
                .setParameter("answerId", answerId));
        return sum.map(Number::intValue).orElse(0);
    }

    @Transactional
    @Override
    public Integer getVotesOfUserByAnswer(Long answerId, Long userId) {
        Optional<Number> sum = SingleResultUtil.getSingleResultOrNull(entityManager
                .createQuery("select sum (v.vote) from AnswerVote v where v.voteAnswerPK.answer.id = :answerId and v.voteAnswerPK.user.id = :userId", Number.class)
                .setParameter("answerId", answerId)
                .setParameter("userId", userId));
        return sum.map(Number::intValue).orElse(0);
    }
}
