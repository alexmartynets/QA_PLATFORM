package com.javamentor.qa.platform.dao.abstracts.dto;

import java.util.List;

public interface DtoCommentDao<T, PK> {

    List<T> getCommentsToAnswer(PK answerId);

    List<T> getCommentsToQuestion(PK questionId);
}
