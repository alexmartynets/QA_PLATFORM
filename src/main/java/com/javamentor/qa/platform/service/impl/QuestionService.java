package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracrt.AbstractQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService extends AbstractQuestionService<Question, Long> {
}
