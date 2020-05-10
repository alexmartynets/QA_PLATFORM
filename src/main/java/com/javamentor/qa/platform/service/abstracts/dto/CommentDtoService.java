package com.javamentor.qa.platform.service.abstracts.dto;

import java.util.List;

public interface CommentDtoService<T, PK> {

    List<T> getCommentsToQuestion(PK questionId);

    List<T> getCommentsToAnswer(PK answerId);


}
