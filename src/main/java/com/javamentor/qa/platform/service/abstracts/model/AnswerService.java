package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;

import java.util.List;

public interface AnswerService extends ReadWriteService<Answer, Long>{
    void deleteById(Long answerId);
}
