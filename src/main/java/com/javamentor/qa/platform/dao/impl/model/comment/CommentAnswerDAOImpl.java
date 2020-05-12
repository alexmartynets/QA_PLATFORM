package com.javamentor.qa.platform.dao.impl.model.comment;

import com.javamentor.qa.platform.dao.abstracts.model.comment.CommentAnswerDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import org.springframework.stereotype.Repository;

@Repository
public class CommentAnswerDAOImpl extends ReadWriteDAOImpl<CommentAnswer, Long> implements CommentAnswerDAO {
}
