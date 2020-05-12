package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentAnswerDtoDao;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.abstracts.dto.CommentAnswerServiceDto;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentAnswerServiceDtoImpl extends ReadWriteServiceImpl<Comment, Long> implements CommentAnswerServiceDto {

    private final CommentAnswerDtoDao commentAnswerDtoDao;

    @Autowired
    public CommentAnswerServiceDtoImpl(CommentAnswerDtoDao commentAnswerDtoDao) {
        super(commentAnswerDtoDao);
        this.commentAnswerDtoDao = commentAnswerDtoDao;
    }

    @Override
    public List<CommentDto> getCommentsToAnswer(Long answerId) {
        return commentAnswerDtoDao.getCommentsToAnswer(answerId);
    }
}
