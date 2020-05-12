package com.javamentor.qa.platform.dao.impl.model.comment;

import com.javamentor.qa.platform.dao.abstracts.model.comment.CommentQuestionDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class CommentQuestionDAOImpl extends ReadWriteDAOImpl<CommentQuestion, Long> implements CommentQuestionDAO {
}
