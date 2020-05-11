package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentQuestionDtoDao;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.service.abstracts.dto.CommentQuestionServiceDto;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentQuestionServiceDtoImpl extends ReadWriteServiceImpl<CommentDto, Long> implements CommentQuestionServiceDto {

    private final CommentQuestionDtoDao commentQuestionDtoDao;

    @Autowired
    public CommentQuestionServiceDtoImpl(CommentQuestionDtoDao commentQuestionDtoDao) {
        super(commentQuestionDtoDao);
        this.commentQuestionDtoDao = commentQuestionDtoDao;
    }

    @Override
    public List<CommentDto> getCommentsToQuestion(Long questionId) {
        return commentQuestionDtoDao.getCommentsToQuestion(questionId);
    }
}
