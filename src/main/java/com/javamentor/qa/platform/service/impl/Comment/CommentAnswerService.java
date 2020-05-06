package com.javamentor.qa.platform.service.impl.Comment;

import com.javamentor.qa.platform.dao.impl.model.CommentDAOImpl;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import org.springframework.stereotype.Service;

@Service
public class CommentAnswerService extends CommentDAOImpl<CommentAnswer, Long> {
}
