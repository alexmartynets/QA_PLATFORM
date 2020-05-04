package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.service.impl.AnswerService;
import com.javamentor.qa.platform.service.impl.QuestionService;
import com.javamentor.qa.platform.webapp.converter.AnswerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/user/question/{questionId}/answer")
public class ResourceAnswerController {

    private AnswerConverter answerConverter;
    private QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public ResourceAnswerController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping("api/user/question/{questionId}/answer")
    public List<AnswerDto> getAnswers(@PathVariable Long questionId) {

        Question question = new Question();
        question.setTitle("TITLE");
        question.setDescription("DESCRIPTION");
        question.setPersistDateTime(LocalDateTime.now());
        questionService.persist(question);

        Answer answer = new Answer();
        answer.setPersistDateTime(LocalDateTime.now());
        answer.setHtmlBody("htmlBody".toUpperCase());
        answerService.persist(answer);

        List<Answer> answers = answerService.getAnswersByQuestionID(questionId);

        List<AnswerDto> answerDtos = answerConverter.answersToDTOs(answers);

        return answerDtos;
    }
}
