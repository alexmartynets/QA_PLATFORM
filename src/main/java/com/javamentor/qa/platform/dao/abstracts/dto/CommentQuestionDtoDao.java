package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.dto.CommentDto;

import java.util.List;

public interface CommentQuestionDtoDao extends ReadWriteDao<CommentDto, Long> {
    List<CommentDto> getCommentsToQuestion(Long questionId);
}
