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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class CommentResourceController {
    @Autowired
    private CommentDtoServiceImpl dtoService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private CommentAnswerServiceImpl commentAnswerService;
    @Autowired
    private CommentQuestionServiceImpl commentQuestionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private CommentConverter converter;

    //     URL общий "/api/user/question/{questionId}/anwer/answer/{answerId}/comment"
    //     Нужно по questionId получить список всех коменнтов к вопросу
    @GetMapping("/question/{questionId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToQuestion(@PathVariable Long questionId) {
        if (questionId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CommentDto> list = dtoService.getCommentsToQuestion(questionId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(list);
    }

    //     Нужно по answerId получить список всех комментов к ответу
    @GetMapping("/answer/{answerId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToAnswer(@PathVariable Long answerId) {
        if (answerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CommentDto> list = dtoService.getCommentsToAnswer(answerId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(list);
    }

    /*
     * URI "/question/ или /answer /{typeId}/comment"
     */
    @PostMapping("/{typeId}/comment")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto, @PathVariable Long typeId) {
        if (commentDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (typeId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // type  ANSWER(0), QUESTION(1)

        if (commentDto.getCommentType() == CommentType.QUESTION) {
            CommentQuestion commentQuestion = new CommentQuestion();

            Comment comment = converter.toComment(commentDto);
            commentQuestion.setComment(comment);

            Question question = questionService.getByKey(typeId);
            commentQuestion.setQuestion(question);

            commentQuestionService.persist(commentQuestion);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
        }
        if (commentDto.getCommentType() == CommentType.ANSWER) {
            CommentAnswer commentAnswer = new CommentAnswer();

            Comment comment = converter.toComment(commentDto);
            commentAnswer.setComment(comment);

            Answer answer = answerService.getByKey(typeId);
            commentAnswer.setAnswer(answer);

            commentAnswerService.persist(commentAnswer);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(commentDto);
    }

    /*
     * URI "/question/ или /answer /comment"
     */
    @PutMapping("/comment")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto) {
        if (commentDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // меняем текст и дату
        // обновить сам Comment по id
        Comment comment = commentService.getByKey(commentDto.getId());
        if (comment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setText(comment.getText());
        comment.setLastUpdateDateTime(LocalDateTime.now());

        commentService.update(comment);
        return ResponseEntity.ok().body(commentDto);
    }

    /*
     * URI "/question/ или /answer /comment/{id}"
     */
    // test Converter
    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id){
        if (id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Comment comment = commentService.getByKey(id);
        if (comment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(converter.toCommentDto(comment));
    }
}

