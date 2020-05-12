package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;

import java.util.List;

public interface CommentAnswerDtoDao extends ReadWriteDao<Comment, Long> {
    List<CommentDto> getCommentsToAnswer(Long answerId);
}
