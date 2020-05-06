package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.impl.CommentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user")   // ("/api/user/question/{questionId}/anwer/answer/{answerId}/comment")
public class CommentResourceController {

    private final CommentServiceImp serviceImp;

    /*
    * Нужно по questionId получить список всех коменнтов к вопросу
    * Какой будет URL у запроса
    * */
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

    /*
    * Нужно по answerId получить список всех комментов к ответу
    * Какой будет URL у запроса
    * */
    @GetMapping("/question/{questionId}/anwer/answer/{answerId}/comment")
    public ResponseEntity<List<Comment>> getCommentsToAnswer(@PathVariable Long questionId, @PathVariable Long answerId) {
        if (answerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Comment> list = serviceImp.getCommentsToAnswer(answerId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(list);
    }

    /*
    * Сохранить комментарий к вопросу/ответу отличать по CommentType брать из сущности?
    * нужен id вопроса/ответа где брать?
    * Какой будет URL у запроса
    * Сосхонять в таблицу CommentQuestion id Question(из запроса) и id Comment(еще не создан) или перезаписывать
    * */
    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        serviceImp.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
    /*
     * Обновлять комментарий к вопросу/ответу отличать по CommentType брать из сущности?
     * нужен id вопроса/ответа где брать?
     * Какой будет URL у запроса
     * Сосхонять в таблицу CommentAnswer id Answer(из запроса) и id Comment(из запроса) или перезаписывать
     * */
    @PutMapping
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        serviceImp.updateComment(comment);
        return ResponseEntity.ok().body(comment);
    }
}
