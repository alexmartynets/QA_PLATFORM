package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentQuestionDao;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.service.abstracts.model.CommentQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentQuestionServiceImpl extends ReadWriteServiceImpl<CommentQuestion, Long> implements CommentQuestionService {

    private final CommentQuestionDao commentQuestionDao;

    @Autowired
    public CommentQuestionServiceImpl(CommentQuestionDao commentQuestionDao) {
        super(commentQuestionDao);
        this.commentQuestionDao = commentQuestionDao;
    }
}
