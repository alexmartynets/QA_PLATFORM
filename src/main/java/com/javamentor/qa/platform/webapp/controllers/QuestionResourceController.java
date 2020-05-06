package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.dao.abstracrt.dto.QuestionDaoDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracrt.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/question")
public class QuestionResourceController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDto>> allQuestions (){
        return ResponseEntity.ok(questionService.getAll());
    }
}