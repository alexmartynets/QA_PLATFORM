package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.impl.dto.CommentDtoServiceImpl;
import com.javamentor.qa.platform.service.impl.model.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")   // ("/api/user/question/{questionId}/anwer/answer/{answerId}/comment")
public class CommentResourceController {
    @Autowired
    private CommentDtoServiceImpl dtoService;
    @Autowired
    private CommentServiceImpl service;


//    @Autowired
//    private final CommentConverter converter;

    //     Нужно по questionId получить список всех коменнтов к вопросу
    @GetMapping("/question/{questionId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToQuestion(@PathVariable Long questionId) {
        if (questionId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CommentDto> list = dtoService.getCommentsToQuestion(questionId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(list);
    }

    //     Нужно по answerId получить список всех комментов к ответу
    @GetMapping("/answer/{answerId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToAnswer(@PathVariable Long answerId) {
        if (answerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CommentDto> list = dtoService.getCommentsToAnswer(answerId);
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
     * @RequestBody Comment comment
     *         if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     * */
    @PostMapping("/comment")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        Comment comment = converter.toComment(commentDto);
        service.persist(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    /*
     * Обновлять комментарий к вопросу/ответу отличать по CommentType брать из сущности?
     * нужен id вопроса/ответа где брать?
     * Какой будет URL у запроса
     * Сосхонять в таблицу CommentAnswer id Answer(из запроса) и id Comment(из запроса) или перезаписывать
     * */
    @PutMapping("/comment")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.update(comment);
        return ResponseEntity.ok().body(comment);
    }
}
