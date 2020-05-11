package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentAnswerDao;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import org.springframework.stereotype.Repository;

@Repository
public class CommentAnswerImpl extends ReadWriteDaoImpl<CommentAnswer, Long> implements CommentAnswerDao {
}
