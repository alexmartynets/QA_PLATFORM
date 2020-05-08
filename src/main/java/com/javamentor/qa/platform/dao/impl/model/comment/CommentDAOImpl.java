package com.javamentor.qa.platform.dao.impl.model.comment;

import com.javamentor.qa.platform.dao.abstracts.model.comment.CommentDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl extends ReadWriteDAOImpl<Comment, Long> implements CommentDAO {
}
