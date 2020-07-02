package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentQuestionDtoDAO;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.abstracts.dto.CommentQuestionServiceDto;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentQuestionServiceDtoImpl extends ReadWriteServiceImpl<Comment, Long> implements CommentQuestionServiceDto {

    private final CommentQuestionDtoDAO commentQuestionDtoDao;

    @Autowired
    public CommentQuestionServiceDtoImpl(CommentQuestionDtoDAO commentQuestionDtoDao) {
        super(commentQuestionDtoDao);
        this.commentQuestionDtoDao = commentQuestionDtoDao;
    }

    @Override
    public List<CommentDto> getCommentsToQuestion(Long questionId) {
        return commentQuestionDtoDao.getCommentsToQuestion(questionId);
    }

    @Override
    public boolean hasUserToCommentQuestion(Long questionId, Long userId) {
        return commentQuestionDtoDao.hasUserCommentQuestion(questionId, userId);
    }
}
