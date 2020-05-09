package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;

    @GetMapping
    public ResponseEntity<List<QuestionDto>> allQuestions (){
        return ResponseEntity.ok(questionDtoService.getAll());
    }
}