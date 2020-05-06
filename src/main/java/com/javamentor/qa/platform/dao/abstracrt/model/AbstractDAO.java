package com.javamentor.qa.platform.dao.abstracrt.model;

import java.util.List;

public interface AbstractDAO<T, PK> {

    void persist(T t);

    void update(T t);

    void delete(T t);

    boolean existsById(PK id);

    T getByKey(PK id);

    List<T> getAll();

}
