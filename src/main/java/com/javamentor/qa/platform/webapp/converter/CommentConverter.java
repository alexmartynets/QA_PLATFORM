package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CommentConverter {

    public abstract Comment toComment(CommentDto userDto);
    public abstract CommentDto toCommentDto(Comment user);
    public abstract List<CommentDto> toListCommentDto(List<Comment> users);

}

