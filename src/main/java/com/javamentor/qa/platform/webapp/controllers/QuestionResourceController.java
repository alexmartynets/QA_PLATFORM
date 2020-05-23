package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.webapp.converter.QuestionConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/question/")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionService questionService;
    private final QuestionConverter questionConverter;

    public QuestionResourceController(QuestionDtoService questionDtoService,
                                      QuestionService questionService,
                                      QuestionConverter questionConverter) {
        this.questionDtoService = questionDtoService;
        this.questionService = questionService;
        this.questionConverter = questionConverter;
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return ResponseEntity.ok(questionDtoService.getAllQuestionDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        if (questionDto.getId().equals(id) && questionService.existsById(id)) {
            Question question = questionConverter.toEntity(questionDto);
            questionService.update(question);
            return ResponseEntity.ok(questionConverter.toDto(question));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        if (questionService.getById(id) != 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}