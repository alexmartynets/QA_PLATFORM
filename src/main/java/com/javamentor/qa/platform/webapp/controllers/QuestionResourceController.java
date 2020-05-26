package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
public class QuestionResourceController {
    @Autowired
    private QuestionDtoService questionDtoService;

    @GetMapping("/pagination")
    public ResponseEntity<Map<Long, List<QuestionDto>>> getPage(){
        return ResponseEntity.ok(questionDtoService.getPage(1, 5));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAll(){
        return ResponseEntity.ok(questionDtoService.getAll());
    }
}
