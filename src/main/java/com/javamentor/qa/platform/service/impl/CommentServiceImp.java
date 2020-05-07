package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.AbstractCommentDAOImpl;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class CommentServiceImp {

    public final AbstractCommentDAOImpl commentDAO;

    public CommentServiceImp(AbstractCommentDAOImpl commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void saveComment(Comment comment) {
        commentDAO.persist(comment);
    }

    public void updateComment(Comment comment) {
        commentDAO.update(comment);
    }

    public List<CommentDto> getCommentsToQuestion(Long questionId) {
        //        List<CommentDto> dtoList = list.stream().map(CommentConverter.INSTANCE::toCommentDto).collect(Collectors.toList());
//       list.forEach(System.out::println);
        return commentDAO.getCommentsToQuestion(questionId);
    }

    public List<CommentDto> getCommentsToAnswer(Long answerId) {

        //        List<CommentDto> dtoList = list.stream().map(CommentConverter.INSTANCE::toCommentDto).collect(Collectors.toList());
//        list.forEach(System.out::println);
        return commentDAO.getCommentsToAnswer(answerId);
    }
}
/*
* Нужен ковертер для сушностей
* */