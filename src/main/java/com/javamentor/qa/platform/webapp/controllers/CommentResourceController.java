package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.service.impl.CommentServiceImp;
import com.javamentor.qa.platform.webapp.converter.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
//      todo  url /api/user/question/{questionId}/anwer/answer/{answerId}/comment
@RequestMapping("/api/user/question/{questionId}/anwer/answer/{answerId}/comment")
public class CommentResourceController {

    private final CommentServiceImp serviceImp;
    private final CommentConverter converter;

//    комментарий к вопросу   вопрос(тип=определен в id)    ответ           /все comment
//    url /api/user/question/{questionId}=5/anwer/answer/{answerId}=/comment


//    комментарий к ответу    вопрос(тип=определен в id)    ответ           /все comment
//    url /api/user/question/{questionId}=3/anwer/answer/{answerId}/comment



//    Для решение тебе нужно сделать сервис дао а также сому ДТО

    //    todo Entity Question Get - нужно по questionId получить список всех коменнтов
    @GetMapping
    public ResponseEntity<List<CommentDto>> getQuestion(@PathVariable Long questionId) {
//        if (questionId == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
////        todo
//        List<CommentDto> dtoList = converter.toListCommentDto(serviceImp.getAll());
//        if (dtoList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(dtoList, HttpStatus.OK);
        return null;
    }

    //    todo Entity Answer Get - Get - нужно по answerId получить список всех комментов
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAnswer(@PathVariable Long answerId) {
//        if (answerId == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
////        todo
//        List<CommentDto> dtoList = converter.toListCommentDto(serviceImp.getAll());
//        if (dtoList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(dtoList, HttpStatus.OK);
        return null;
    }
//        todo POST - в качестве параметров придет CommentDto ее нужно сконверировать и сохранить в бд
    @PostMapping
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto) {
//        if (commentDto == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Comment comment = converter.toComment(commentDto);
//        serviceImp.persist(comment);
//        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
        return null;
    }
//       todo PUT - в качестве параметров придет CommentDto с id ее нужно сконверировать и обновить в бд
    @PutMapping
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto) {
//        if (commentDto == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Comment comment = converter.toComment(commentDto);
//        serviceImp.update(comment);
//        return new ResponseEntity<>(commentDto, HttpStatus.OK);
        return null;
    }

}
