package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;

    public QuestionResourceController(QuestionDtoService questionDtoService) {
        this.questionDtoService = questionDtoService;
    }

    @RequestMapping(value = "/pagination", method = RequestMethod.POST)
    public ResponseEntity<Map<Long, List<QuestionDto>>> getPage(@RequestParam int page,
                                                                @RequestParam int size) {
        return ResponseEntity.ok(questionDtoService.getPage(page, size));
    }

    @GetMapping
    public ResponseEntity< List<QuestionDto>> allQuestions (){
        return ResponseEntity.ok(questionDtoService.getAll());
    }

}
