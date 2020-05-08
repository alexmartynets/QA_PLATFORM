package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.service.impl.dto.CommentDtoServiceImpl;
import com.javamentor.qa.platform.service.impl.model.CommentAnswerServiceImpl;
import com.javamentor.qa.platform.service.impl.model.CommentQuestionServiceImpl;
import com.javamentor.qa.platform.service.impl.model.CommentServiceImpl;
import com.javamentor.qa.platform.webapp.converter.CommentConverter;
import lombok.NonNull;
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
    private CommentConverter converter;
    // URL общий "/api/user/question/{questionId}/anwer/answer/{answerId}/comment"

    @GetMapping("/question/{questionId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToQuestion(@PathVariable @NonNull Long questionId) {

        List<CommentDto> list = dtoService.getCommentsToQuestion(questionId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/answer/{answerId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsToAnswer(@PathVariable @NonNull Long answerId) {

        List<CommentDto> list = dtoService.getCommentsToAnswer(answerId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(list);
    }

    // URI "/question/ или /answer /{typeId}/comment"
    @PostMapping("/{typeId}/comment")
    public ResponseEntity<CommentDto> saveComment(@RequestBody @NonNull CommentDto commentDto,
                                                  @PathVariable @NonNull Long typeId) {
        Comment comment = converter.toComment(commentDto);

        if (commentDto.getCommentType() == CommentType.QUESTION) {
            CommentQuestion commentQuestion = commentQuestionService.getCommentQuestion(comment, typeId);
            commentQuestionService.persist(commentQuestion);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
        }
        if (commentDto.getCommentType() == CommentType.ANSWER) {
            CommentAnswer commentAnswer = commentAnswerService.getCommentAnswer(comment, typeId);
            commentAnswerService.persist(commentAnswer);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(commentDto);
    }

    // URI "/question/ или /answer /comment", меняем текст и дату
    @PutMapping("/comment")
    public ResponseEntity<CommentDto> updateComment(@RequestBody @NonNull CommentDto commentDto) {

        Comment comment = commentService.getByKey(commentDto.getId());
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setText(comment.getText());
        comment.setLastUpdateDateTime(LocalDateTime.now());

        commentService.update(comment);
        return ResponseEntity.ok().body(commentDto);
    }

    // URI "/question/ или /answer /comment/{id}",  test Converter
    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable @NonNull Long id) {

        Comment comment = commentService.getByKey(id);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(converter.toCommentDto(comment));
    }
}

/*
    @PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
    Student savedStudent = studentRepository.save(student);

	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

	return ResponseEntity.created(location).build(); }
 */

