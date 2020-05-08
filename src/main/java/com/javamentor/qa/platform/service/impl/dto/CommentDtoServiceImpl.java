package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.impl.dto.DtoCommentDaoImpl;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.service.abstracts.dto.CommentDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDtoServiceImpl implements CommentDtoService<CommentDto, Long> {
    @Autowired
    public DtoCommentDaoImpl commentDaoDto;

    @Override
    public List<CommentDto> getCommentsToQuestion(Long questionId) {
        return commentDaoDto.getCommentsToQuestion(questionId);
    }

    @Override
    public List<CommentDto> getCommentsToAnswer(Long answerId) {
        return commentDaoDto.getCommentsToAnswer(answerId);

    }
}
