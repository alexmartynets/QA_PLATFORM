package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import javafx.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;

    public QuestionResourceController( QuestionDtoService questionDtoService) {
        this.questionDtoService = questionDtoService;
    }


    @PostMapping(value = "/pagination")
    public ResponseEntity<Pair<Long, List<QuestionDto>>> getPaginationQuestion(@RequestParam int page,
                                                                               @RequestParam int size) {
        return ResponseEntity.ok(questionDtoService.getPaginationQuestion(page, size));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAll() {
        return ResponseEntity.ok(questionDtoService.getAll());
    }
}
