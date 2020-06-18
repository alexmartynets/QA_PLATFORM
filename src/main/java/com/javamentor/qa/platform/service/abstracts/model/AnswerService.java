package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;

import java.util.Map;

public interface AnswerService extends ReadWriteService<Answer, Long>{
    public void resetIsHelpful(Long questionId);
}
