package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CommentAnswerConverter {

    @Mapping(source = "commentDto.text", target = "comment.text")
    @Mapping(source = "commentDto.commentType", target = "comment.commentType")
    @Mapping(source = "commentDto.userId", target = "comment.user.id")
    @Mapping(source = "answerId", target = "answer.id")
    public abstract CommentAnswer toCommentAnswer(CommentDto commentDto, Long answerId);

}
