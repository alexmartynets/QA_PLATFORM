package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuetionsTabResourceController {
    private final QuestionDtoService questionDtoService;
    private final SearchQuestionDAO searchQuestionDAO;

    @Autowired
    public QuetionsTabResourceController(QuestionDtoService questionDtoService, SearchQuestionDAO searchQuestionDAO) {
        this.questionDtoService = questionDtoService;
        this.searchQuestionDAO = searchQuestionDAO;
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getQuestionsSortedByCountValuable() {
        return ResponseEntity.ok(searchQuestionDAO.getQuestionsSortedByVotes());
    }

    @GetMapping("/tagged/{mainTagId}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByTagId(@PathVariable Long mainTagId) {
        List<QuestionDto> abc = questionDtoService.getQuestionsByTagId(mainTagId);
        return ResponseEntity.ok(questionDtoService.getQuestionsByTagId(mainTagId));
    }


    @ApiOperation(value = "Получение списка с watchTag")
    @PostMapping("/watchTag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список получен"),
            @ApiResponse(code = 400, message = "Список не получен")
    })
    public ResponseEntity<List<QuestionDto>> getQuestionsSortedByWatchTag(@RequestBody TagDto tags) {
        List<QuestionDto> watchTag = searchQuestionDAO.getQuestionsSortedByVotes();
        for (QuestionDto element : watchTag) {
            for (TagDto e : element.getTags()) {
                String name = e.getName();
                if (name.equals(tags.getName())) {
                    element.setWatchTag(true);
                }
            }
        }
        return ResponseEntity.ok(watchTag);
    }

    @ApiOperation(value = "Получение списка с ignoreTag")
    @PostMapping("/ignoreTag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список получен"),
            @ApiResponse(code = 400, message = "Список не получен")
    })
    public ResponseEntity<List<QuestionDto>> getQuestionsSortedByIgnoreTag(@RequestBody TagDto tags) {
        List<QuestionDto> ignoreTag = searchQuestionDAO.getQuestionsSortedByVotes();
        for (QuestionDto element : ignoreTag) {
            for (TagDto e : element.getTags()) {
                String name = e.getName();
                if (name.equals(tags.getName())) {
                    element.setIgnoreTag(true);
                }
            }
        }
        return ResponseEntity.ok(ignoreTag);
    }

}
