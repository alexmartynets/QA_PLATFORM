package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;

public interface AnswerVoteService extends ReadWriteService<AnswerVote, Long> {
    Boolean addAnswerVotePlus(Long questionId, Long answerId, Long userId);
    Boolean addAnswerVoteMinus(Long questionId, Long answerId, Long userId);
//    Boolean getVoteOfUserByAnswer(Long answerId, Long questionId, Long userId);
    Integer getVotesOfAnswer(Long answerId);
}
