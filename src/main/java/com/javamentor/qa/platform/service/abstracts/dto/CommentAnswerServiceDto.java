package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.abstracts.model.ReadWriteService;

import java.util.List;

public interface CommentAnswerServiceDto extends ReadWriteService<Comment, Long> {
    List<CommentDto> getCommentsToAnswer(Long answerId);
}
