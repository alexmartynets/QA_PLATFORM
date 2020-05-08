package com.javamentor.qa.platform.service.impl.model.Comment;

import com.javamentor.qa.platform.dao.abstracts.model.comment.CommentAnswerDAO;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.service.abstracts.model.comment.CommentAnswerService;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentAnswerServiceImpl extends ReadWriteServiceImpl<CommentAnswer, Long> implements CommentAnswerService {

    @Autowired
    private final CommentAnswerDAO commentAnswerDAO;

    public CommentAnswerServiceImpl(CommentAnswerDAO commentAnswerDAO) {
        super(commentAnswerDAO);
        this.commentAnswerDAO = commentAnswerDAO;
    }
}
