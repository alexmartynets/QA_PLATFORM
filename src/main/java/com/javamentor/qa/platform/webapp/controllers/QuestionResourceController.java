package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/question")
public class QuestionResourceController {

    @Autowired
    private QuestionDtoService questionDtoService;

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDto>> allQuestions (){
        return ResponseEntity.ok(questionDtoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id){
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(id));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto){
//        Question question = questionConverter.toEntity(questionDto);
//        question.setId(id);
//        questionService.update(question);
//        return ResponseEntity.ok(questionDto);
//    }

    @DeleteMapping("/{id}")//todo не работает
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id){
        questionService.delete(questionService.getByKey(id));
        return ResponseEntity.ok(getQuestionById(id));
    }

}
