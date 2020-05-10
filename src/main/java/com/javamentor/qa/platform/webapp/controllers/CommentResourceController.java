package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.service.impl.dto.CommentDtoServiceImpl;
import com.javamentor.qa.platform.service.impl.model.*;
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

    private final CommentDtoServiceImpl dtoService;
    private final CommentServiceImpl commentService;
    private final CommentAnswerServiceImpl commentAnswerService;
    private final CommentQuestionServiceImpl commentQuestionService;
    private final CommentConverter converter;
    private final AnswerServiceImpl answerService;
    private final QuestionServiceImpl questionService;

    // URL общий "/api/user/question/{questionId}/anwer/answer/{answerId}/comment"

    @GetMapping("/question/{questionId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToQuestion(@PathVariable @NonNull Long questionId) {

        List<CommentDto> list = dtoService.getCommentsToQuestion(questionId);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/answer/{answerId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToAnswer(@PathVariable @NonNull Long answerId) {

        List<CommentDto> list = dtoService.getCommentsToAnswer(answerId);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/{typeId}/comment")
    public ResponseEntity<CommentDto> saveComment(@RequestBody @NonNull CommentDto commentDto,
                                                  @PathVariable @NonNull Long typeId) {
        Comment comment = converter.toComment(commentDto);

        if (comment.getCommentType() == CommentType.QUESTION) {
            Question question = questionService.getByKey(typeId);
            if (question == null) {
                return ResponseEntity.notFound().build();
            }
            CommentQuestion commentQuestion = commentQuestionService.getCommentQuestion(comment, question);
            commentQuestionService.persist(commentQuestion);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
        }
        if (comment.getCommentType() == CommentType.ANSWER) {
            Answer answer = answerService.getByKey(typeId);
            if (answer == null) {
                return ResponseEntity.notFound().build();
            }
            CommentAnswer commentAnswer = commentAnswerService.getCommentAnswer(comment, answer);
            commentAnswerService.persist(commentAnswer);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(commentDto);
    }

    @PutMapping("/comment")
    public ResponseEntity<CommentDto> updateComment(@RequestBody @NonNull CommentDto commentDto) {

        Comment comment = commentService.getByKey(commentDto.getId());
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Comment updatedComment = commentService.getUpdateComment(comment, commentDto);
        commentService.update(updatedComment);
        return ResponseEntity.ok().body(commentDto);
    }

    // test Converter
    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable @NonNull Long id) {

        Comment comment = commentService.getByKey(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(converter.toCommentDto(comment));
    }
}


