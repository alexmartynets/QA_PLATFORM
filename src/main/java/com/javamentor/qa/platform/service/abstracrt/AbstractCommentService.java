package com.javamentor.qa.platform.service.abstracrt;

public interface AbstractCommentService<T, PK> {

    void persist(T comment);

    void update(T comment);

    T getByKey(PK id);
}
