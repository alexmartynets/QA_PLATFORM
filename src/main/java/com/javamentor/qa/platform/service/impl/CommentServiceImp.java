package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.abstracrt.AbstractCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImp implements AbstractCommentService<Comment, Long> {

    private AbstractDAOImpl abstractDAO;

    public void persist(Comment comment) {
        abstractDAO.persist(comment);
    }

    public void update(Comment comment) {
        abstractDAO.update(comment);
    }
}
