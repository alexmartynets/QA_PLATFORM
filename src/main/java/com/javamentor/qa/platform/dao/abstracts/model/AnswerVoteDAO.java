package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;

import java.util.List;

public interface AnswerVoteDAO extends ReadWriteDAO<AnswerVote, Long> {
    List<AnswerVote> getAllVotesByAnswerId(Long answerId);
    List<AnswerVote> getVotesOfUserByAnswer(Long answerId, Long userId);
}
