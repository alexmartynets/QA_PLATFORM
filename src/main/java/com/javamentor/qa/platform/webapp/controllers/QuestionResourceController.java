package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.service.impl.UserServisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/question")
public class QuestionResourceController {


    @Autowired
    private UserServisTest userServisTest;

    @GetMapping
    public ResponseEntity<String> getQuestion (){
        userServisTest.addUser();
        return ResponseEntity.ok("Тест секьюрити");
    }

}
