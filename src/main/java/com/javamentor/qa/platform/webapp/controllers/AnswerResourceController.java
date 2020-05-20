package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.webapp.converter.AnswerConverter;

import com.javamentor.qa.platform.webapp.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user/question/{questionId}/answer")
public class AnswerResourceController {

    private final AnswerConverter answerConverter;
    private final AnswerService answerService;
    private final AnswerDtoService answerDtoService;

    public AnswerResourceController(AnswerConverter answerConverter, AnswerService answerService, AnswerDtoService answerDtoService) {
        this.answerConverter = answerConverter;
        this.answerService = answerService;
        this.answerDtoService = answerDtoService;
    }

    @GetMapping
    public ResponseEntity<List<AnswerDto>> getAnswersDto(@PathVariable @NotNull Long questionId) {
        return ResponseEntity.ok(answerDtoService.getAnswersDtoByQuestionId(questionId));
    }

    @PostMapping
    public ResponseEntity<AnswerDto> addAnswer(@RequestBody @Validated(OnCreate.class) AnswerDto answerDTO, @PathVariable @NotNull Long questionId) {
        answerDTO.setQuestionId(questionId);
        Answer answer = answerConverter.dtoToAnswer(answerDTO);
        answerService.persist(answer);
        return ResponseEntity.ok(answerDTO);
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerDto> updateAnswer(@RequestBody @Validated(OnUpdate.class) AnswerDto answerDTO, @PathVariable @NotNull Long answerId, @PathVariable @NotNull Long questionId) {
        Answer answer = answerConverter.dtoToAnswer(answerDTO);
        answer.setId(answerId);
        if (answer.getIsHelpful()) {
            answerService.resetIsHelpful(questionId);
            answer.setDateAcceptTime(LocalDateTime.now());
        }
        answerService.update(answer);
        return ResponseEntity.ok(answerConverter.answerToDto(answer));
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<AnswerDto> deleteAnswer(@PathVariable @NotNull Long answerId) {
        answerService.deleteById(answerId);
        return ResponseEntity.ok().build();
    }
}