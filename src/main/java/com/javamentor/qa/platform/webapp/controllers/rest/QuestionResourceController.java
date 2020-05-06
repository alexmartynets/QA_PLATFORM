package com.javamentor.qa.platform.webapp.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/question")
public class QuestionResourceController {

    @GetMapping
    public ResponseEntity<String> getQuestion (){
        return ResponseEntity.ok("Тест секьюрити");
    }

}
