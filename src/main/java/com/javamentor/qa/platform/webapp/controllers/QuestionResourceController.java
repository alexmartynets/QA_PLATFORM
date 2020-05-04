package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/question")
public class QuestionResourceController {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @GetMapping
//    public ResponseEntity<List<QuestionDto>> questionPage() {
//        userService.getAll();
//        return ResponseEntity.ok().build();
//    }
}