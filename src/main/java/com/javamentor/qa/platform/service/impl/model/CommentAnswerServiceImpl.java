package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import org.springframework.stereotype.Service;

@Service
public class CommentAnswerServiceImpl extends ReadWriteServiceImpl<CommentAnswer, Long> implements CommentAnswerService {

}
