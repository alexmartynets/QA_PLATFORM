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
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment, @PathVariable Long typeId) {
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (typeId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // type  ANSWER(0), QUESTION(1)

        if (comment.getCommentType() == CommentType.QUESTION) {
            CommentQuestion commentQuestion = new CommentQuestion();
            commentQuestion.setComment(comment);
            Question question = questionService.getByKey(typeId);
            commentQuestion.setQuestion(question);
            commentQuestionService.persist(commentQuestion);
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        }
        if (comment.getCommentType() == CommentType.ANSWER) {
            CommentAnswer commentAnswer = new CommentAnswer();
            commentAnswer.setComment(comment);
            Answer answer = answerService.getByKey(typeId);
            commentAnswer.setAnswer(answer);
            commentAnswerService.persist(commentAnswer);
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(comment);
    }

    /*
     * URI "/question/ или /answer /comment"
     */
    @PutMapping("/comment")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // меняем текст и дату
        // обновить сам Comment по id

        Comment comment1 = commentService.getByKey(comment.getId());

        if (comment1 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        comment1.setText(comment.getText());
        comment1.setLastUpdateDateTime(LocalDateTime.now());

        commentService.update(comment1);

        return ResponseEntity.ok().body(comment);
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

