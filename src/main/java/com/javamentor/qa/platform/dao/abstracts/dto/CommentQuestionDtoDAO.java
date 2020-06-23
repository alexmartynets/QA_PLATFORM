package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDAO;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;

import java.util.List;

public interface CommentQuestionDtoDAO extends ReadWriteDAO<Comment, Long> {

    List<CommentDto> getCommentsToQuestion(Long questionId);

    Boolean hasUserCommentQuestion (Long questionId, Long userId);
}
