package com.javamentor.qa.platform.service.impl.Comment;

import com.javamentor.qa.platform.dao.impl.model.CommentDAOImpl;
import com.javamentor.qa.platform.models.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends CommentDAOImpl<Comment, Long> {
}
