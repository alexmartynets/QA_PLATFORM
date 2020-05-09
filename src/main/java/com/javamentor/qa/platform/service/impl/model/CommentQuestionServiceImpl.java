package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentQuestionDao;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracts.model.CommentQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentQuestionServiceImpl extends ReadWriteServiceImpl<CommentQuestion, Long> implements CommentQuestionService {


    @Autowired
    public CommentQuestionServiceImpl(CommentQuestionDao commentQuestionDao) {
        super(commentQuestionDao);
    }

    @Override
    public CommentQuestion getCommentQuestion(Comment comment, Question question) {
        comment.setPersistDateTime(LocalDateTime.now());
        comment.setLastUpdateDateTime(LocalDateTime.now());
        return CommentQuestion.builder()
                .comment(comment)
                .question(question)
                .build();
    }
}
