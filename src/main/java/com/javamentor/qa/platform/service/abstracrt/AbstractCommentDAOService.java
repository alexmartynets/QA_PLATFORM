package com.javamentor.qa.platform.service.abstracrt;

import java.util.List;

public interface AbstractCommentDAOService<T, PK> {

    List<T> getCommentsToQuestion(PK questionId);

    List<T> getCommentsToAnswer(PK answerId);
}
