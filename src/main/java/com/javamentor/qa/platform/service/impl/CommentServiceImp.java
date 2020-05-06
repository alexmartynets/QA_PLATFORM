package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.AbstractCommentDAOImpl;
import com.javamentor.qa.platform.models.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CommentServiceImp {

    public final AbstractCommentDAOImpl commentDAO;

    public void saveComment(Comment comment) {
        commentDAO.persist(comment);
    }

    public void updateComment(Comment comment) {
        commentDAO.update(comment);
    }

    public List<Comment> getCommentsToQuestion(Long questionId) {
        //        List<CommentDto> dtoList = list.stream().map(CommentConverter.INSTANCE::toCommentDto).collect(Collectors.toList());
//       list.forEach(System.out::println);
        return commentDAO.getCommentsToQuestion(questionId);
    }

    public List<Comment> getCommentsToAnswer(Long answerId) {

        //        List<CommentDto> dtoList = list.stream().map(CommentConverter.INSTANCE::toCommentDto).collect(Collectors.toList());
//        list.forEach(System.out::println);
        return commentDAO.getCommentsToAnswer(answerId);
    }
}
/*
* Нужен ковертер для сушностей
* */