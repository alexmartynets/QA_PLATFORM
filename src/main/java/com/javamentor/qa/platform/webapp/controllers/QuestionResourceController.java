package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.service.abstracts.model.VoteQuestionService;
import com.javamentor.qa.platform.webapp.converter.QuestionConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
@RestControllerAdvice
@RestController
@RequestMapping(value = "/api/user/question/", produces = "application/json")
@Api(value = "QuestionApi", description = "Операции с вопросами (создание, изменение, получение списка, получение вопроса по ID)")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionService questionService;
    private final UserService userService;
    private final QuestionConverter questionConverter;
    private final VoteQuestionService voteQuestionService;
    private final SearchQuestionDAO searchQuestionDAO;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public QuestionResourceController(QuestionDtoService questionDtoService,
                                      QuestionService questionService,
                                      UserService userService,
                                      QuestionConverter questionConverter,
                                      VoteQuestionService voteQuestionService, SearchQuestionDAO searchQuestionDAO) {
        this.questionDtoService = questionDtoService;
        this.questionService = questionService;
        this.userService = userService;
        this.questionConverter = questionConverter;
        this.voteQuestionService = voteQuestionService;
        this.searchQuestionDAO = searchQuestionDAO;
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

    public ResponseEntity<?> getQuestionById(@PathVariable @NotNull Long id, @RequestParam Long userId) {
        Optional<QuestionDto> questionDto = questionDtoService.getQuestionDtoById(id, userId);
        if (questionDto.isPresent()) {
            return ResponseEntity.ok(questionDto.get());
        }
        logger.error(String.format("Вопрос с указанным ID: %d не найден", id));
        return ResponseEntity.badRequest().body(String.format("No question with ID %d", id));
    }

    @Validated(OnUpdate.class)
    @ApiOperation(value = "Изменение заголовка и описания вопроса (параметр ID обязателен)")
    @PutMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные о вопросе обновлены"),
            @ApiResponse(code = 400, message = "Данные о вопросе не были обновлены")
    })
    public ResponseEntity<?> updateQuestionTitleAndDescription(@PathVariable @NotNull Long id,
                                                               @RequestBody @Valid QuestionDto questionDtoFromClient) {
        if (!questionDtoFromClient.getId().equals(id)) {
            logger.error(String.format("Переданный в QuestionDto ID: %d не совпадает с url: %d", questionDtoFromClient.getId(), id));
            return ResponseEntity.badRequest().body("Different ID by Dto and URL");
        }
        Question question = questionService.getByKey(id);
        if (question == null) {
            logger.error(String.format("Запрос на изменение вопроса с неактуальным ID: %d.", id));
            return ResponseEntity.badRequest().body(String.format("Can't find Question with ID %d", id));
        }
        question.setTitle(questionDtoFromClient.getTitle());
        question.setDescription(questionDtoFromClient.getDescription());
        questionService.update(question);
        return ResponseEntity.ok(questionConverter.toDto(question));
    }

    @ApiOperation(value = "Голосование за вопрос (параметр ID обязателен)")
    @PostMapping(path = "/{questionId}/upVote")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Голос учтён"),
            @ApiResponse(code = 400, message = "Голос не учтён")
    })
    public ResponseEntity<?> toUpVoteForQuestion(@PathVariable @NotNull Long questionId, @RequestParam Long userId) {
        Question question = questionService.getByKey(questionId);
        if (question == null) {
            logger.error(String.format("Вопрос с ID: %d не найден", questionId));
            return ResponseEntity.badRequest().body(String.format("Can't find Question with ID %d", questionId));
        }
        if (questionDtoService.isUserCanToVoteByQuestionUp(questionId, userId)) {
            logger.error(String.format("Попытка второй раз проголосовать за вопрос " +
                    "с ID: %d пользователем с ID: %d", questionId, userId));
            return ResponseEntity.badRequest().body("Only one time User can to vote by Question.");
        }
        User user = userService.getByKey(userId);
        voteQuestionService.persist(new VoteQuestion(user, question, 1));
        return ResponseEntity.ok(questionDtoService.getCountValuableQuestionWithUserVote(questionId, userId));
    }

    @ApiOperation(value = "Голосование за вопрос (параметр ID обязателен)")
    @PostMapping(path = "/{questionId}/downVote")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Голос учтён"),
            @ApiResponse(code = 400, message = "Голос не учтён")
    })
    public ResponseEntity<?> toDownVoteForQuestion(@PathVariable @NotNull Long questionId, @RequestParam Long userId) {
        Question question = questionService.getByKey(questionId);
        if (question == null) {
            logger.error(String.format("Вопрос с ID: %d не найден", questionId));
            return ResponseEntity.badRequest().body(String.format("Can't find Question with ID %d", questionId));
        }
        if (questionDtoService.isUserCanToVoteByQuestionDown(questionId, userId)) {
            logger.error(String.format("Попытка второй раз проголосовать за вопрос " +
                    "с ID: %d пользователем с ID: %d", questionId, userId));
            return ResponseEntity.badRequest().body("Only one time User can to vote by Question.");
        }
        User user = userService.getByKey(userId);
        voteQuestionService.persist(new VoteQuestion(user, question, -1));
        return ResponseEntity.ok(questionDtoService.getCountValuableQuestionWithUserVote(questionId, userId));
    }

    @ApiOperation(value = "Удаление вопроса (параметр ID обязателен)")
    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вопрос удалён"),
            @ApiResponse(code = 400, message = "Вопрос не может быть удалён")
    })
    public ResponseEntity<?> deleteQuestion(@PathVariable @NotNull Long id) {
        Optional<QuestionDto> questionDto = questionDtoService.hasQuestionAnswer(id);
        if (!questionDto.isPresent()) {
            logger.error(String.format("Вопрос с ID: %d не найден", id));
            return ResponseEntity.badRequest().body(String.format("Can't find Question with ID %d", id));
        }
        if (questionDto.get().getCountAnswer() > 0) {
            logger.error(String.format("На вопрос был дан ответ, поэтому вопрос с ID: %d не может быть удалён", id));
            return ResponseEntity.badRequest().body(String.format("Can't delete question with ID %d. Question has answer", id));
        }
        questionService.deleteByFlag(id);
        return ResponseEntity.ok().body(String.format("Deleted Question with ID %d, is successful", id));
    }

    @ApiOperation(value = "Получение списка вопросов по ID пользователя")
    @GetMapping(path = "/by-user/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список вопросов выгружен"),
            @ApiResponse(code = 400, message = "Задан не корректный ID пользователя")
    })
    public ResponseEntity<?> getQuestionByUserId(@PathVariable @NotNull Long id) {
        if (!userService.existsById(id)) {
            logger.error(String.format("Получен запрос на список вопросов от пользователя с неактуальным ID: %d.", id));
            return ResponseEntity.badRequest().body(String.format("Can't find User with ID %d", id));
        }
        logger.info(String.format("Получен список вопросов пользователя с ID: %d.", id));
        return ResponseEntity.ok(questionDtoService.getQuestionDtoListByUserId(id));
    }


    @ApiOperation(value = "Получение списка пагинации из QuestionDto (без фильтра)")
    @GetMapping(value = "/pagination")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список для пагинации из QuestionDto получен")
    })
    public ResponseEntity<Pair<Long, List<QuestionDto>>> getPaginationQuestion(@RequestParam @Min(value = 1) int page,
                                                                               @RequestParam @Min(value = 1) int size) {
        return ResponseEntity.ok(questionDtoService.getPaginationQuestion(page, size));
    }

    @ApiOperation(value = "Возможность голосования 'за' пользователю в данном вопросе")
    @GetMapping(value = "/{id}/check-up")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Результат получен")
    })
    public ResponseEntity<?> checkForToVoteUp(@PathVariable @NotNull Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(questionDtoService.isUserCanToVoteByQuestionUp(id, userId));
    }

    @ApiOperation(value = "Возможность голосования 'против' пользователю в данном вопросе")
    @GetMapping(value = "/{id}/check-down")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Результат получен")
    })
    public ResponseEntity<?> checkForToVoteDown(@PathVariable @NotNull Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(questionDtoService.isUserCanToVoteByQuestionDown(id, userId));
    }

    @ApiOperation(value = "Добавление вопроса")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вопрос добавлен"),
    })
    @Validated(OnCreate.class)
    public ResponseEntity<?> addQuestion(@RequestBody @Valid QuestionDto questionDto) {
        Question question = questionConverter.toEntity(questionDto);
        questionService.persist(question);
        logger.info(String.format("Вопрос с заголовком: %s добавлен в базу данных", questionDto.getTitle()));
        return ResponseEntity.ok().body(question.getId());
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsSortedByCountValuable() {
        return ResponseEntity.ok(searchQuestionDAO.getQuestionsSortedByVotes());
    }

    @GetMapping("/tagged/{mainTagId}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByTagId(@PathVariable Long mainTagId) {
        return ResponseEntity.ok(questionDtoService.getQuestionsByTagId(mainTagId));
    }

    @GetMapping("/unanswered")
    public ResponseEntity<List<QuestionDto>> getUnansweredQuestions() {
        return ResponseEntity.ok(questionDtoService.getUnansweredQuestions());
    }
}