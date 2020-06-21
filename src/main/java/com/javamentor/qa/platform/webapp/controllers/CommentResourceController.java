package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.service.abstracts.dto.CommentAnswerServiceDto;
import com.javamentor.qa.platform.service.abstracts.dto.CommentQuestionServiceDto;
import com.javamentor.qa.platform.service.abstracts.model.comment.CommentAnswerService;
import com.javamentor.qa.platform.service.abstracts.model.comment.CommentQuestionService;
import com.javamentor.qa.platform.webapp.converter.CommentAnswerConverter;
import com.javamentor.qa.platform.webapp.converter.CommentConverter;
import com.javamentor.qa.platform.webapp.converter.CommentQuestionConverter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class CommentResourceController {

    private final CommentQuestionServiceDto commentQuestionServiceDto;
    private final CommentAnswerServiceDto commentAnswerServiceDto;
    private final CommentQuestionService commentQuestionService;
    private final CommentAnswerService commentAnswerService;
    private final CommentConverter commentConverter;
    private final CommentAnswerConverter answerConverter;
    private final CommentQuestionConverter questionConverter;

    public CommentResourceController(CommentQuestionServiceDto commentQuestionServiceDto,
                                     CommentAnswerServiceDto commentAnswerServiceDto,
                                     CommentQuestionService commentQuestionService,
                                     CommentAnswerService commentAnswerService,
                                     CommentConverter commentConverter,
                                     CommentAnswerConverter answerConverter,
                                     CommentQuestionConverter questionConverter) {
        this.commentQuestionServiceDto = commentQuestionServiceDto;
        this.commentAnswerServiceDto = commentAnswerServiceDto;
        this.commentQuestionService = commentQuestionService;
        this.commentAnswerService = commentAnswerService;
        this.commentConverter = commentConverter;
        this.answerConverter = answerConverter;
        this.questionConverter = questionConverter;
    }

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
        CommentQuestion commentQuestion = questionConverter.toCommentQuestion(commentDto, questionId);
        commentQuestionService.persist(commentQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    @PostMapping("/answer/{answerId}/comment")
    public ResponseEntity<CommentDto> saveCommentAnswer(@RequestBody @NonNull CommentDto commentDto,
                                                        @PathVariable @NonNull Long answerId) {
        CommentAnswer commentAnswer = answerConverter.toCommentAnswer(commentDto, answerId);
        commentAnswerService.persist(commentAnswer);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    @PutMapping("/question/comment")
    public ResponseEntity<CommentDto> updateCommentQuestion(@RequestBody @NonNull CommentDto commentDto) {
        Comment comment = commentQuestionServiceDto.getByKey(commentDto.getId());
        comment.setText(commentDto.getText());
        commentQuestionServiceDto.update(comment);
        return ResponseEntity.ok().body(commentConverter.toCommentDto(comment));
    }

    @PutMapping("/answer/comment")
    public ResponseEntity<?> updateCommentAnswer(@RequestBody @NonNull CommentDto commentDto,
                                                 @RequestParam @NotNull Long userId) {
        if (commentDto.getUserId().equals(userId)) {
            Comment comment = commentAnswerServiceDto.getByKey(commentDto.getId());
            comment.setText(commentDto.getText());
            commentAnswerServiceDto.update(comment);
            return ResponseEntity.ok().body(commentConverter.toCommentDto(comment));
        }else {
            return ResponseEntity.badRequest().body(String.format("Пользователь с id %d не может изменить комментарий который он не давал.", userId));
        }
    }
}