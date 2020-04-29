package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.service.impl.UserServisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/question")
public class QuestionResourceController {


    @Autowired
    private UserServisTest userServisTest;

    @GetMapping()
    public ResponseEntity<String> getQuestion(){
        userServisTest.deleteUser2(2l);
        return ResponseEntity.ok("Тест секьюрити");
    }

}
