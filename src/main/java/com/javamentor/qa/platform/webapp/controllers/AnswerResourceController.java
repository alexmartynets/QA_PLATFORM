package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.webapp.converter.AnswerConverter;

import com.javamentor.qa.platform.webapp.converter.UserConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/user/question/{questionId}/answer", produces = "application/json")
@Api(value = "AnswerApi", description = "Операции с ответами (создание, изменение, получение списка, получение ответов по ID вопроса)")
public class AnswerResourceController {

    private final AnswerConverter answerConverter;
    private final AnswerService answerService;
    private final AnswerDtoService answerDtoService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AnswerResourceController(AnswerConverter answerConverter, AnswerService answerService, AnswerDtoService answerDtoService) {
        this.answerConverter = answerConverter;
        this.answerService = answerService;
        this.answerDtoService = answerDtoService;
    }

    @ApiOperation(value = "Получение ответов по ID вопроса с сортировкой по недавно добавленным/измененным")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ответы получены")})
    @GetMapping
    public ResponseEntity<List<AnswerDto>> getAnswersDto(@PathVariable @NotNull Long questionId) {
        return ResponseEntity.ok(answerDtoService.getAnswersDtoByQuestionId(questionId));
    }

    @ApiOperation(value = "Получение ответов по ID вопроса с сортировкой по счетчику полезности")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ответы получены")})
    @GetMapping("/sort/count")
    public ResponseEntity<List<AnswerDto>> getAnswersDtoSortCount(@PathVariable @NotNull Long questionId) {
        return ResponseEntity.ok(answerDtoService.getAnswersDtoByQuestionIdSortCount(questionId));
    }

    @ApiOperation(value = "Получение ответов по ID вопроса с сортировкой по дате")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ответы получены")})
    @GetMapping("/sort/date")
    public ResponseEntity<List<AnswerDto>> getAnswersDtoSortDate(@PathVariable @NotNull Long questionId) {
        return ResponseEntity.ok(answerDtoService.getAnswersDtoByQuestionIdSortDate(questionId));
    }

    @ApiOperation(value = "Добавление ответа по ID вопроса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ответ добавлен"),
            @ApiResponse(code = 400, message = "ID вопроса в url и в dto не совпадают")
    })
    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<AnswerDto> addAnswer(@RequestBody @Valid AnswerDto answerDTO,
                                               @PathVariable @NotNull Long questionId) {
        if (questionId.equals(answerDTO.getQuestionId())) {
            Answer answer = answerConverter.dtoToAnswer(answerDTO);
            answerService.persist(answer);
            logger.info(String.format("Ответ к вопросу с ID: %s добавлен в базу данных", answerDTO.getQuestionId()));
            return ResponseEntity.ok(answerDTO);
        } else {
            logger.error(String.format("Ответ к вопросу с ID: %s не добавлен в базу данных (в url ID вопроса: %s)",
                    answerDTO.getQuestionId(), questionId));
            return ResponseEntity.status(400).body(answerDTO);
        }
    }

    @ApiOperation(value = "Изменение ответа по ID вопроса и ID ответа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ответ изменен"),
            @ApiResponse(code = 400, message = "ID вопроса или ответа в url и в dto не совпадают")
    })
    @Validated(OnUpdate.class)
    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerDto> updateAnswer(@RequestBody @Valid AnswerDto answerDTO,
                                                  @PathVariable @NotNull Long answerId,
                                                  @PathVariable @NotNull Long questionId) {
        if (questionId.equals(answerDTO.getQuestionId()) && answerId.equals(answerDTO.getId())) {
            Answer answer = answerConverter.dtoToAnswer(answerDTO);
            answer.setId(answerId);
            if (answer.getIsHelpful()) {
                answerService.resetIsHelpful(questionId);
                if (answer.getDateAcceptTime() == null) {
                    answer.setDateAcceptTime(LocalDateTime.now());
                }
            } else {
                answer.setDateAcceptTime(null);
            }
            if (!answerService.getByKey(answerId).getHtmlBody().equals(answer.getHtmlBody())) {
                answer.setUpdateDateTime(LocalDateTime.now());
            }
            answerService.update(answer);
            logger.info(String.format("Ответ с ID: %s к вопросу с ID: %s изменен", answerDTO.getId(), answerDTO.getQuestionId()));
            return ResponseEntity.ok(answerConverter.answerToDto(answer));
        } else {
            logger.error(String.format("Ответ с ID: %s к вопросу с ID: %s не добавлен в базу данных (в url ID ответа: %s, ID вопроса: %s)",
                    answerDTO.getId(), answerDTO.getQuestionId(), answerId, questionId));
            return ResponseEntity.status(400).body(answerDTO);
        }
    }

    @ApiOperation(value = "Удаление ответа по ID вопроса и ID ответа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ответ удален"),
    })
    @DeleteMapping("/{answerId}")
    public ResponseEntity<String> deleteAnswer(@PathVariable @NotNull Long answerId) {
        answerService.deleteByKeyCascadeEnable(answerId);
            logger.info(String.format("Ответ с ID: %s удален", answerId));
            return ResponseEntity.ok().build();

    }
}
