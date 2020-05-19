package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CommentQuestionConverter {

    @Mapping(source = "commentDto.text", target = "comment.text")
    @Mapping(source = "commentDto.commentType", target = "comment.commentType")
    @Mapping(source = "commentDto.userId", target = "comment.user.id")
    @Mapping(source = "questionId", target = "question.id")
    public abstract CommentQuestion toCommentQuestion(CommentDto commentDto, Long questionId);
}
