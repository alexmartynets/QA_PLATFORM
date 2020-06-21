package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;

public interface AnswerVotesService extends ReadWriteService<AnswerVote, Long> {
    void addAnswerVote(Long answerId, Long userId, Boolean count);
    Integer getVoteOfUserByAnswer(Long answerId, Long userId);
    Integer getVotesOfAnswer(Long answerId);
}
