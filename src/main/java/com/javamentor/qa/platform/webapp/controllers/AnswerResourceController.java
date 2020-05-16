package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.webapp.converter.AnswerConverter;

import com.javamentor.qa.platform.webapp.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/question/{questionId}/answer")
public class AnswerResourceController {


    private final AnswerConverter answerConverter;
    private final AnswerService answerService;
    private final AnswerDtoService answerDtoService;
    private final UserConverter userConverter;

    @Autowired
    public AnswerResourceController(AnswerConverter answerConverter, AnswerService answerService, AnswerDtoService answerDtoService, UserConverter userConverter) {
        this.answerConverter = answerConverter;
        this.answerService = answerService;
        this.answerDtoService = answerDtoService;
        this.userConverter = userConverter;
    }

    @GetMapping
    public ResponseEntity<List<AnswerDto>> getAnswersDto(@PathVariable Long questionId) {
        return ResponseEntity.ok(answerDtoService.getAnswersDtoByQuestionId(questionId));
    }

    @PostMapping
    public ResponseEntity<AnswerDto> addAnswer(@RequestBody AnswerDto answerDTO, @PathVariable Long questionId) {
        answerDTO.setQuestionId(questionId);
        Answer answer = answerConverter.dtoToAnswer(answerDTO);
        answerService.persist(answer);
        return ResponseEntity.ok(answerDTO);
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerDto> updateAnswer(@RequestBody AnswerDto answerDTO, @PathVariable Long answerId, @PathVariable Long questionId) {
        Answer answer = answerConverter.dtoToAnswer(answerDTO);
        answer.setId(answerId);
        answer.getQuestion().setId(questionId);
        if(answer.getIsHelpful() && answerService.getByKey(answerId).getIsHelpful()){
            answerService.update(answer);
        }
        answerService.resetIsHelpful(questionId);
        answerService.update(answer);
        return ResponseEntity.ok(answerConverter.answerToDto(answer));
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<AnswerDto> deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteById(answerId);
        return ResponseEntity.ok().build();
    }
}
