package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.AnswerDAOImpl;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.springframework.stereotype.Service;

@Service
public class AnswerService extends AnswerDAOImpl<Answer, Long> {
}
