package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentDao;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.abstracts.model.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl extends ReadWriteServiceImpl<Comment, Long> implements CommentService {

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        super(commentDao);
    }

    @Override
    public Comment getUpdateComment(Comment comment, CommentDto commentDto) {
        comment.setText(commentDto.getText());
        comment.setLastUpdateDateTime(LocalDateTime.now());
        return comment;
    }
}
