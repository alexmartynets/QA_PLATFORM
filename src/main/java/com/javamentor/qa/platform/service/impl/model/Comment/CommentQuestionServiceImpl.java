package com.javamentor.qa.platform.service.impl.model.Comment;

import com.javamentor.qa.platform.dao.abstracts.model.comment.CommentQuestionDAO;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentQuestionServiceImpl extends ReadWriteServiceImpl<CommentQuestion, Long> implements CommentQuestionDAO {

    private final CommentQuestionDAO commentQuestionDAO;

    public CommentQuestionServiceImpl(CommentQuestionDAO commentQuestionDAO) {
        super(commentQuestionDAO);
        this.commentQuestionDAO = commentQuestionDAO;
    }
}
