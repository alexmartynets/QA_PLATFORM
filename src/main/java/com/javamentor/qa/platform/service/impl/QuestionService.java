package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.QuestionDAOImpl;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends QuestionDAOImpl<Question, Long> {
}
