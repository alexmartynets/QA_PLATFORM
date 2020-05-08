package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CommentConverter {

    @Mapping(source = "commentDto.id", target = "id")
    @Mapping(source = "commentDto.text", target = "text")
    @Mapping(source = "commentDto.commentType", target = "commentType")
    @Mapping(source = "commentDto.persistDateTime", target = "persistDateTime")
    @Mapping(source = "commentDto.lastUpdateDateTime", target = "lastUpdateDateTime")
    @Mapping(source = "commentDto.userId", target = "user.id")
    public abstract Comment toComment(CommentDto commentDto);

    @Mapping(source = "comment.id", target = "id")
    @Mapping(source = "comment.text", target = "text")
    @Mapping(source = "comment.commentType", target = "commentType")
    @Mapping(source = "comment.persistDateTime", target = "persistDateTime")
    @Mapping(source = "comment.lastUpdateDateTime", target = "lastUpdateDateTime")
    @Mapping(source = "comment.user.id", target = "userId")
    public abstract CommentDto toCommentDto(Comment comment);

}


