package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.impl.dto.CommentDaoDtoImpl;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.service.abstracts.dto.CommentDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDtoServiceImpl implements CommentDtoService<CommentDto, Long> {

    public CommentDtoServiceImpl(CommentDaoDtoImpl commentDaoDto) {
    }

    @Override
    public List<CommentDto> getCommentsToQuestion(Long questionId) {
        //        return commentDAO.getCommentsToQuestion(questionId);
        return null;
    }

    @Override
    public List<CommentDto> getCommentsToAnswer(Long answerId) {
        //        return commentDAO.getCommentsToAnswer(answerId);
        return null;
    }
}
