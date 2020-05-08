package com.javamentor.qa.platform.service.impl.model.Comment;

import com.javamentor.qa.platform.dao.abstracts.model.comment.CommentDAO;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.abstracts.model.comment.CommentService;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ReadWriteServiceImpl<Comment, Long> implements CommentService {

    private final CommentDAO commentDAO;

    public CommentServiceImpl(CommentDAO commentDAO) {
        super(commentDAO);
        this.commentDAO = commentDAO;
    }
}
