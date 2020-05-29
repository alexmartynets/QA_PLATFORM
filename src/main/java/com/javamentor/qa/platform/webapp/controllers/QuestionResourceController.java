package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.webapp.converter.QuestionConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/question/")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionService questionService;
    private final QuestionConverter questionConverter;
    private final UserService userService;

    public QuestionResourceController(QuestionDtoService questionDtoService,
                                      QuestionService questionService,
                                      QuestionConverter questionConverter,
                                      UserService userService) {
        this.questionDtoService = questionDtoService;
        this.questionService = questionService;
        this.questionConverter = questionConverter;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return ResponseEntity.ok(questionDtoService.getAllQuestionDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<QuestionDto> questionDto = questionDtoService.getQuestionDtoById(id);
        return questionDto.isPresent() ? ResponseEntity.ok(questionDto) : ResponseEntity.badRequest().body("Вопроса с таким id не существует");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        if (!questionDto.getId().equals(id)){
            return ResponseEntity.badRequest().body("id в url не совпадают с переданной questionDto");
        }
        if (!questionService.existsById(id)) {
            return ResponseEntity.badRequest().body("Вопроса c таким id не существует");
        }
        Question question = questionConverter.toEntity(questionDto);
        questionService.update(question);
        return ResponseEntity.ok(questionConverter.toDto(question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        if (questionService.existsById(id)){
            questionService.deleteByKeyCascadeEnable(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Вопроса с таким id не существует");
    }

    @GetMapping("/by-user/{id}")
    public ResponseEntity<?> getQuestionByUserId(@PathVariable Long id) {
        if (userService.existsById(id)) {
            return ResponseEntity.ok(questionDtoService.getQuestionDtoListByUserId(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}