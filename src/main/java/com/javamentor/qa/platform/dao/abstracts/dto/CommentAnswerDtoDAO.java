package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDAO;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;

import java.util.List;

public interface CommentAnswerDtoDAO extends ReadWriteDAO<Comment, Long> {

    List<CommentDto> getCommentsToAnswer(Long answerId);

    Boolean hasUserCommentAnswer (Long answerId, Long userId);
}
