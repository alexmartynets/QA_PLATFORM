package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.impl.CommentServiceImp;
import com.javamentor.qa.platform.webapp.converter.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user")   // ("/api/user/question/{questionId}/anwer/answer/{answerId}/comment")
public class CommentResourceController {

    private final CommentServiceImp serviceImp;

    //  todo Get - нужно по questionId получить список всех коменнтов
    @GetMapping("/question/{questionId}/comment")
    public ResponseEntity<List<Comment>> getCommentsToQuestion(@PathVariable Long questionId) {
        if (questionId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Comment> list = serviceImp.getCommentsToQuestion(questionId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(list);
    }

    //  todo Get - Get - нужно по answerId получить список всех комментов
    @GetMapping("/answer/{answerId}/comment")
    public ResponseEntity<List<Comment>> getCommentsToAnswer(@PathVariable Long answerId) {
        if (answerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Comment> list = serviceImp.getCommentsToAnswer(answerId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        serviceImp.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        serviceImp.updateComment(comment);
        return ResponseEntity.ok().body(comment);
    }
}
