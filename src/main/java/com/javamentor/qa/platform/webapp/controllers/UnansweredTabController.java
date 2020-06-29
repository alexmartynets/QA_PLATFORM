package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/unanswered")
public class UnansweredTabController {
    private final QuestionDtoService questionDtoService;

    @Autowired
    public UnansweredTabController(QuestionDtoService questionDtoService) {
        this.questionDtoService = questionDtoService;
    }

    @GetMapping
    public List<QuestionDto> getUnansweredQuestions() {
        return questionDtoService.getUnansweredQuestions();
    }
}
