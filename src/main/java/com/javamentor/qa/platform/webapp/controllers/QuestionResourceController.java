package com.javamentor.qa.platform.webapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class QuestionResourceController {

    @GetMapping
    public ResponseEntity<String> getQuestion() {
        return ResponseEntity.ok("Тест секьюрити");
    }

}

