package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentAnswerDao;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentAnswerServiceImpl extends ReadWriteServiceImpl<CommentAnswer, Long> implements CommentAnswerService {


    @Autowired
    public CommentAnswerServiceImpl(CommentAnswerDao commentAnswerDao) {
        super(commentAnswerDao);
    }

    @Override
    public CommentAnswer getCommentAnswer(Comment comment, Answer answer) {
        comment.setPersistDateTime(LocalDateTime.now());
        comment.setLastUpdateDateTime(LocalDateTime.now());
        return CommentAnswer.builder()
                .comment(comment)
                .answer(answer)
                .build();
    }
}
