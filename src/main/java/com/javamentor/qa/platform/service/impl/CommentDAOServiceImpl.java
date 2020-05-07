//package com.javamentor.qa.platform.service.impl;
//
//import com.javamentor.qa.platform.dao.impl.model.CommentDAOImpl;
//import com.javamentor.qa.platform.models.dto.CommentDto;
//import com.javamentor.qa.platform.service.abstracrt.AbstractCommentDAOService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//@Transactional
//public class CommentDAOServiceImpl implements AbstractCommentDAOService<CommentDto, Long> {
//    @Autowired
//    private CommentDAOImpl commentDAO;
//
//    public List<CommentDto> getCommentsToQuestion(Long questionId) {
//        return commentDAO.getCommentsToQuestion(questionId);
//    }
//
//    public List<CommentDto> getCommentsToAnswer(Long answerId) {
//        return commentDAO.getCommentsToAnswer(answerId);
//    }
//}
