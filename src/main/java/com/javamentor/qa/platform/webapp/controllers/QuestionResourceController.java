package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.exception.ApiRequestException;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public QuestionResourceController( QuestionDtoService questionDtoService) {
        this.questionDtoService = questionDtoService;
    }

    @ApiOperation(value = "Получение списка пагинации из QuestionDto (без фильтра)")
    @PostMapping(value = "/pagination")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список для пагинации из QuestionDto получен")
    })
    public ResponseEntity<Pair<Long, List<QuestionDto>>> getPaginationQuestion(@RequestParam(defaultValue = "1") int page,
                                                                               @RequestParam(defaultValue = "5") int size) {
        if(page < 1 || size < 1 ){
            throw new ApiRequestException("Значения не должны быть отрицательными");
        } else {
            return ResponseEntity.ok(questionDtoService.getPaginationQuestion(page, size));
        }
    }

    @ApiOperation(value = "получение списка доступных вопросов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список вопросов получен")
    })
    public ResponseEntity<List<QuestionDto>> getAll() {
        return ResponseEntity.ok(questionDtoService.getAll());
    }

}
