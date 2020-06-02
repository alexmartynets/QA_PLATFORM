package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.webapp.converter.QuestionConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
@RestController
@RequestMapping(value = "/api/user/question/", produces = "application/json")
@Api(value="QuestionApi", description = "Операции с вопросами (создание, изменение, получение списка, получение вопроса по ID)")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionService questionService;
    private final QuestionConverter questionConverter;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public QuestionResourceController(QuestionDtoService questionDtoService,
                                      QuestionService questionService,
                                      QuestionConverter questionConverter,
                                      UserService userService) {
        this.questionDtoService = questionDtoService;
        this.questionService = questionService;
        this.questionConverter = questionConverter;
        this.userService = userService;
    }

    @ApiOperation(value = "Получение списка вопросов, которые не удалены")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список вопросов получен")
    })
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return ResponseEntity.ok(questionDtoService.getAllQuestionDto());
    }

    @ApiOperation(value = "Получение вопроса (параметр ID обязателен)")
    @GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вопрос найден"),
            @ApiResponse(code = 404, message = "Вопрос не найден")
    })
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<QuestionDto> questionDto = questionDtoService.getQuestionDtoById(id);
        if (questionDto.isPresent()){
            return ResponseEntity.ok(questionDto.get());
        }
        logger.error(String.format("Вопрос с указанным ID: %d не найден", id));
        return ResponseEntity.badRequest().body("Вопроса c таким id не существует");
    }

    @ApiOperation(value = "Изменение данных о вопросе (параметр ID обязателен)")
    @PutMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные о вопросе обновлены"),
            @ApiResponse(code = 400, message = "Данные о вопросе не были обновлены")
    })
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        if (!questionDto.getId().equals(id)) {
            logger.error(String.format("Переданный в QuestionDto ID: %d не совпадает с url: %d", questionDto.getId(), id));
            return ResponseEntity.badRequest().body("id в url не совпадают с переданной questionDto");
        }
        if (!questionService.existsById(id)) {
            logger.error(String.format("Вопроса с ID: %d не существует", id));
            return ResponseEntity.badRequest().body("Вопроса c таким id не существует");
        }
        Question question = questionConverter.toEntity(questionDto);
        questionService.update(question);
        return ResponseEntity.ok(questionConverter.toDto(question));
    }

    @ApiOperation(value = "Удаление вопроса (параметр ID обязателен)")
    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вопрос удалён"),
            @ApiResponse(code = 400, message = "Вопрос не может быть удалён")
    })
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        Optional<QuestionDto> questionDto = questionDtoService.getQuestionDtoById(id);
        if (!questionDto.isPresent()) {
            logger.error(String.format("Вопрос с ID: %d не найден", id));
            return ResponseEntity.badRequest().body("Вопроса с таким id не существует");
        }
        if (questionDto.get().getCountAnswer() > 0) {
            logger.error(String.format("На вопрос был дан ответ, поэтому вопрос с ID: %d не может быть удалён", id));
            return ResponseEntity.badRequest().body("Невозможно удалить вопрос, на который был дан ответ");
        }
        questionService.delete(questionConverter.toEntity(questionDto.get()));
        return ResponseEntity.ok().body(String.format("Удаление вопроса с ID: %d выполнено успешно", id));
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