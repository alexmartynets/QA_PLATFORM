package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.service.abstracts.dto.CommentAnswerServiceDto;
import com.javamentor.qa.platform.service.abstracts.dto.CommentQuestionServiceDto;
import com.javamentor.qa.platform.service.abstracts.model.*;
import com.javamentor.qa.platform.service.abstracts.model.comment.CommentAnswerService;
import com.javamentor.qa.platform.service.abstracts.model.comment.CommentQuestionService;
import com.javamentor.qa.platform.webapp.converter.CommentConverter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class CommentResourceController {

    private final CommentQuestionServiceDto commentQuestionServiceDto;
    private final CommentAnswerServiceDto commentAnswerServiceDto;
    private final CommentConverter converter;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CommentQuestionService commentQuestionService;
    private final CommentAnswerService commentAnswerService;

    @GetMapping("/question/{questionId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToQuestion(@PathVariable @NonNull Long questionId) {

        List<CommentDto> list = commentQuestionServiceDto.getCommentsToQuestion(questionId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/answer/{answerId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToAnswer(@PathVariable @NonNull Long answerId) {

        List<CommentDto> list = commentAnswerServiceDto.getCommentsToAnswer(answerId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/question/{questionId}/comment")
    public ResponseEntity<CommentDto> saveCommentQuestion(@RequestBody @NonNull CommentDto commentDto,
                                                          @PathVariable @NonNull Long questionId) {
        Comment comment = converter.toComment(commentDto);
        Question question = questionService.getByKey(questionId);
        CommentQuestion commentQuestion = CommentQuestion.builder()
                .comment(comment)
                .question(question).build();

        commentQuestionService.persist(commentQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    @PostMapping("/answer/{answerId}/comment")
    public ResponseEntity<CommentDto> saveCommentAnswer(@RequestBody @NonNull CommentDto commentDto,
                                                        @PathVariable @NonNull Long answerId) {
        Comment comment = converter.toComment(commentDto);
        Answer answer = answerService.getByKey(answerId);
        CommentAnswer commentAnswer = CommentAnswer.builder()
                .comment(comment)
                .answer(answer)
                .build();

        commentAnswerService.persist(commentAnswer);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    @PutMapping("/question/comment")
    public ResponseEntity<CommentDto> updateCommentQuestion(@RequestBody @NonNull CommentDto commentDto) {

        Comment comment = commentQuestionServiceDto.getByKey(commentDto.getId());
        comment.setText(commentDto.getText());
        commentQuestionServiceDto.update(comment);
        return ResponseEntity.ok().body(commentDto);
    }

    @PutMapping("/answer/comment")
    public ResponseEntity<CommentDto> updateCommentAnswer(@RequestBody @NonNull CommentDto commentDto) {

        Comment comment = commentAnswerServiceDto.getByKey(commentDto.getId());
        comment.setText(commentDto.getText());
        commentAnswerServiceDto.update(comment);
        return ResponseEntity.ok().body(commentDto);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable @NonNull Long id) {

        Comment comment = commentQuestionServiceDto.getByKey(id);
        return ResponseEntity.ok().body(converter.toCommentDto(comment));
    }
}


