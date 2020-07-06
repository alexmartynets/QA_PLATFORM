package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;

import java.util.List;

public interface AnswerVoteDAO extends ReadWriteDAO<AnswerVote, Long> {
    Integer getAllVotesByAnswerId(Long answerId);
    Integer getVotesOfUserByAnswer(Long answerId, Long userId);
}
