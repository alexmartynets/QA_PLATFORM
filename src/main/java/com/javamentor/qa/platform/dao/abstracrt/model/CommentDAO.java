package com.javamentor.qa.platform.dao.abstracrt.model;

import java.util.List;

public interface CommentDAO<T, PK> {

    List<T> getCommentsToQuestion(PK questionId);

    List<T> getCommentsToAnswer(PK answerId);
}
