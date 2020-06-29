package com.javamentor.qa.platform.webapp.controllers;import com.javamentor.qa.platform.models.dto.AnswerDto;import com.javamentor.qa.platform.models.entity.question.answer.Answer;import com.javamentor.qa.platform.models.util.action.OnCreate;import com.javamentor.qa.platform.models.util.action.OnUpdate;import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;import com.javamentor.qa.platform.service.abstracts.model.AnswerService;import com.javamentor.qa.platform.service.abstracts.model.AnswerVoteService;import com.javamentor.qa.platform.service.abstracts.model.QuestionService;import com.javamentor.qa.platform.webapp.converter.AnswerConverter;import io.swagger.annotations.Api;import io.swagger.annotations.ApiOperation;import io.swagger.annotations.ApiResponse;import io.swagger.annotations.ApiResponses;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.validation.annotation.Validated;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;import javax.validation.constraints.NotNull;import java.util.List;@Validated@RestController@RequestMapping(value = "/api/user/question/{questionId}/answer", produces = "application/json")@Api(value = "AnswerApi", description = "Операции с ответами (создание, изменение, получение списка, получение ответов по ID вопроса)")public class AnswerResourceController {    private final AnswerConverter answerConverter;    private final AnswerService answerService;    private final QuestionService questionService;    private final AnswerVoteService answerVoteService;    private final AnswerDtoService answerDtoService;    private final Logger logger = LoggerFactory.getLogger(this.getClass());    public AnswerResourceController(AnswerConverter answerConverter, AnswerService answerService, QuestionService questionService, AnswerVoteService answerVoteService, AnswerDtoService answerDtoService) {        this.answerConverter = answerConverter;        this.answerService = answerService;        this.questionService = questionService;        this.answerVoteService = answerVoteService;        this.answerDtoService = answerDtoService;    }    @ApiOperation(value = "Получение ответов по ID вопроса с сортировкой по недавно добавленным/измененным")    @ApiResponses(value = {            @ApiResponse(code = 200, message = "Ответы получены")})    @GetMapping    public ResponseEntity<List<AnswerDto>> getAnswersDtoSortNew(@PathVariable @NotNull Long questionId, @RequestParam Long userId) {        return ResponseEntity.ok(answerDtoService.getAnswersDtoByQuestionIdSortNew(questionId, userId));    }    @ApiOperation(value = "Получение ответов по ID вопроса с сортировкой по счетчику полезности")    @ApiResponses(value = {            @ApiResponse(code = 200, message = "Ответы получены")})    @GetMapping("/sort/count")    public ResponseEntity<List<AnswerDto>> getAnswersDtoSortCount(@PathVariable @NotNull Long questionId, @RequestParam Long userId) {        return ResponseEntity.ok(answerDtoService.getAnswersDtoByQuestionIdSortCount(questionId, userId));    }    @ApiOperation(value = "Получение ответов по ID вопроса с сортировкой по дате создания")    @ApiResponses(value = {            @ApiResponse(code = 200, message = "Ответы получены")})    @GetMapping("/sort/date")    public ResponseEntity<List<AnswerDto>> getAnswersDtoSortDate(@PathVariable @NotNull Long questionId, @RequestParam Long userId) {        return ResponseEntity.ok(answerDtoService.getAnswersDtoByQuestionIdSortDate(questionId, userId));    }    @ApiOperation(value = "Добавление ответа по ID вопроса")    @ApiResponses(value = {            @ApiResponse(code = 200, message = "Ответ добавлен"),            @ApiResponse(code = 400, message = "ID вопроса в url и в dto не совпадают")    })    @Validated(OnCreate.class)    @PostMapping    public ResponseEntity<?> addAnswer(@RequestBody @Valid AnswerDto answerDTO,                                       @PathVariable @NotNull Long questionId) {        if (questionId.equals(answerDTO.getQuestionId())) {            if (questionService.getByKey(questionId) == null) {                logger.info(String.format("Ответ с id %d не существует в БД.", questionId));                return ResponseEntity.badRequest().body(String.format("Ответ с id %d не существует в БД.", questionId));            }            if (answerDtoService.isUserNotAnswered(questionId, answerDTO.getUserDto().getId())) {                Answer answer = answerConverter.dtoToAnswer(answerDTO);                answerService.persist(answer);                logger.info(String.format("Ответ к вопросу с ID: %s добавлен в базу данных", answerDTO.getQuestionId()));                return ResponseEntity.status(HttpStatus.CREATED).body(answerDTO);            } else {                logger.info(String.format("Повторная попытка пользователя с id %s добавить ответ по вопросу c id %s", answerDTO.getUserDto().getId(), questionId));                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Пользователь с id %s уже дал ответ по вопросу c id %s", answerDTO.getUserDto().getId(), questionId));            }        } else {            logger.error(String.format("Ответ к вопросу с ID: %s не добавлен в базу данных (в url ID вопроса: %s)",                    answerDTO.getQuestionId(), questionId));            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(answerDTO);        }    }    @ApiOperation(value = "Изменение текста ответа по ID вопроса и ID ответа. (ID != null)")    @ApiResponses(value = {            @ApiResponse(code = 200, message = "Ответ изменен"),            @ApiResponse(code = 400, message = "ID вопроса или ответа в url и в dto не совпадают")})    @Validated(OnUpdate.class)    @PatchMapping("/{answerId}/body")    public ResponseEntity<?> updateAnswerBody(@RequestBody @Valid AnswerDto answerDto,                                              @PathVariable @NotNull Long answerId,                                              @PathVariable @NotNull Long questionId,                                              @RequestParam @NotNull Long userId) {        if (questionId.equals(answerDto.getQuestionId()) && answerId.equals(answerDto.getId())) {            if (answerDto.getUserDto().getId().equals(userId)) {                Answer answer = answerService.updateAnswerBody(answerId, questionId, userId, answerDto.getHtmlBody());                logger.info(String.format("Ответ с ID: %s к вопросу с ID: %s изменен. htmlBody=%s", answerId, questionId, answer.getHtmlBody()));                return ResponseEntity.ok(answerConverter.answerToDto(answer));            } else {                return ResponseEntity.badRequest().body(String.format("Пользователь с id %d не может изменить ответ который он не давал.", userId));            }        } else {            logger.error(String.format("Ответ с ID: %s к вопросу с ID: %s не изменен (в url ID ответа: %s, ID вопроса: %s)",                    answerDto.getId(), answerDto.getQuestionId(), answerId, questionId));            return ResponseEntity.badRequest().body(String.format("Ответ с ID: %s к вопросу с ID: %s не изменен (в url ID ответа: %s, ID вопроса: %s)",                    answerDto.getId(), answerDto.getQuestionId(), answerId, questionId));        }    }    @ApiOperation(value = "Изменение счетчика ответа по ID вопроса и ID ответа. ID != null. " +            "ID в url должны соответствовать ID в dto." +            "Параметр запроса 'userId' - id голосующего пользователя.")    @ApiResponses(value = {            @ApiResponse(code = 200, message = "Счетчик ответа изменен."),            @ApiResponse(code = 400, message = "ID вопроса или ответа в url и в dto не совпадают.")})    @Validated(OnUpdate.class)    @PatchMapping("/{answerId}/votePlus")    public ResponseEntity<?> updateAnswerVotePlus(@RequestBody @Valid AnswerDto answerDto, @PathVariable @NotNull Long answerId,                                                  @PathVariable @NotNull Long questionId, @RequestParam @NotNull Long userId) {        if (questionId.equals(answerDto.getQuestionId()) && answerId.equals(answerDto.getId())) {            if (answerVoteService.addAnswerVotePlus(questionId, answerId, userId)) {                answerDto.setCountValuable(answerVoteService.getVotesOfAnswer(answerId));                logger.info(String.format("Ответ с ID: %s к вопросу с ID: %s изменен. countValuable=%s", answerId, questionId, answerDto.getCountValuable()));                return ResponseEntity.ok(answerDto);            } else {                logger.error(String.format("Пользователь id %s уже голосовал за ответ id %s", userId, answerId));                return ResponseEntity.badRequest().body(String.format("Пользователь id %s уже голосовал за ответ id %s", userId, answerId));            }        } else {            logger.error(String.format("Ответ с ID: %s к вопросу с ID: %s не изменен (в url ID ответа: %s, ID вопроса: %s)",                    answerDto.getId(), answerDto.getQuestionId(), answerId, questionId));            return ResponseEntity.badRequest().body(String.format("Ответ с ID: %s к вопросу с ID: %s не изменен (в url ID ответа: %s, ID вопроса: %s)",                    answerDto.getId(), answerDto.getQuestionId(), answerId, questionId));        }    }    @ApiOperation(value = "Понижение счетчика ответа по ID вопроса и ID ответа. ID != null. " +            "ID в url должны соответствовать ID в dto." +            "Параметр запроса 'userId' - id голосующего пользователя.")    @ApiResponses(value = {            @ApiResponse(code = 200, message = "Счетчик ответа изменен."),            @ApiResponse(code = 400, message = "ID вопроса или ответа в url и в dto не совпадают.")})    @Validated(OnUpdate.class)    @PatchMapping("/{answerId}/voteMinus")    public ResponseEntity<?> updateAnswerVoteMinus(@RequestBody @Valid AnswerDto answerDto, @PathVariable @NotNull Long answerId,                                                   @PathVariable @NotNull Long questionId, @RequestParam @NotNull Long userId) {        if (questionId.equals(answerDto.getQuestionId()) && answerId.equals(answerDto.getId())) {            if (answerVoteService.addAnswerVoteMinus(questionId, answerId, userId)) {                answerDto.setCountValuable(answerVoteService.getVotesOfAnswer(answerId));                logger.info(String.format("Ответ с ID: %s к вопросу с ID: %s изменен. countValuable=%s", answerId, questionId, answerDto.getCountValuable()));                return ResponseEntity.ok(answerDto);            } else {                logger.error(String.format("Пользователь id %s уже голосовал за ответ id %s", userId, answerId));                return ResponseEntity.badRequest().body(String.format("Пользователь id %s уже голосовал за ответ id %s", userId, answerId));            }        } else {            logger.error(String.format("Ответ с ID: %s к вопросу с ID: %s не изменен (в url ID ответа: %s, ID вопроса: %s)",                    answerDto.getId(), answerDto.getQuestionId(), answerId, questionId));            return ResponseEntity.badRequest().body(String.format("Ответ с ID: %s к вопросу с ID: %s не изменен (в url ID ответа: %s, ID вопроса: %s)",                    answerDto.getId(), answerDto.getQuestionId(), answerId, questionId));        }    }    @ApiOperation(value = "Изменение полезности ответа по ID вопроса и ID ответа. ID != null. ID в url должны соответствовать ID в dto.")    @ApiResponses(value = {            @ApiResponse(code = 200, message = "Отметка полезности ответа изменена."),            @ApiResponse(code = 400, message = "ID вопроса или ответа в url и в dto не совпадают.")})    @Validated(OnUpdate.class)    @PatchMapping("/{answerId}/helpful")    public ResponseEntity<?> updateAnswerHelpful(@RequestBody @Valid AnswerDto answerDto,                                                 @PathVariable @NotNull Long answerId,                                                 @PathVariable @NotNull Long questionId,                                                 @RequestParam @NotNull Long userId) {        if (questionId.equals(answerDto.getQuestionId()) && answerId.equals(answerDto.getId())) {            Answer answer = answerService.updateAnswerHelpful(answerId, questionId, userId, answerDto.getIsHelpful());            logger.info(String.format("Ответ с ID: %s к вопросу с ID: %s изменен isHelpful=%s", answerId, questionId, answerDto.getIsHelpful()));            return ResponseEntity.ok(answerConverter.answerToDto(answer));        } else {            logger.error(String.format("Ответ с ID: %s к вопросу с ID: %s не изменен (в url ID ответа: %s, ID вопроса: %s)",                    answerDto.getId(), answerDto.getQuestionId(), answerId, questionId));            return ResponseEntity.badRequest().body(String.format("Ответ с ID: %s к вопросу с ID: %s не изменен (в url ID ответа: %s, ID вопроса: %s)",                    answerDto.getId(), answerDto.getQuestionId(), answerId, questionId));        }    }    @ApiOperation(value = "Удаление ответа по ID вопроса и ID ответа")    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ответ удален")})    @DeleteMapping("/{answerId}")    public ResponseEntity<String> deleteAnswer(@PathVariable @NotNull Long answerId) {        answerService.deleteByFlagById(answerId);        logger.info(String.format("Ответ с ID: %s удален", answerId));        return ResponseEntity.ok().build();    }}