package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;

public interface CommentAnswerService extends ReadWriteService<CommentAnswer, Long> {

    CommentAnswer getCommentAnswer(Comment comment, Answer answer);
}
