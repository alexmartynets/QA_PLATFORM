package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/question/{questionId}/anwer/answer/{answerId}/comment")
public class CommentResourceController {

    //    url /api/user/question/{questionId}/anwer/answer/{answerId}/comment
//    Get - нужно по answerId получить список всех комментов
//    Get - нужно по questionId получить список всех коменнтов
//    POST - в качестве параметров придет CommentDto ее нужно сконверировать и сохранить в бд
//    PUT - в качестве параметров придет CommentDto с id ее нужно сконверировать и обновить в бд
//    Для решение тебе нужно сделать сервис дао а также сому ДТО

    @GetMapping
    public ResponseEntity<List<String>> getQuestion(@PathVariable Long id) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<String>> getAnswer(@PathVariable Long id) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

}
