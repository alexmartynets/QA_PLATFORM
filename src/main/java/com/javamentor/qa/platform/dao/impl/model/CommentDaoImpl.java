package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentDao;
import com.javamentor.qa.platform.models.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends ReadWriteDaoImpl<Comment, Long> implements CommentDao {
}
