package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentQuestionDao;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class CommentQuestionDaoImpl extends ReadWriteDaoImpl<CommentQuestion, Long> implements CommentQuestionDao {

}
