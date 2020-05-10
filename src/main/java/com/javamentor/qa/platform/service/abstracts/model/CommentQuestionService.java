package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;

public interface CommentQuestionService extends ReadWriteService<CommentQuestion, Long> {

    CommentQuestion getCommentQuestion(Comment comment, Question question);
}
