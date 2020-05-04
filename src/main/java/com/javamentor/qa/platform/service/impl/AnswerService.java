package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.service.abstracrt.AbstractAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AnswerService extends AbstractAnswerService<Answer,Long>  {

}
