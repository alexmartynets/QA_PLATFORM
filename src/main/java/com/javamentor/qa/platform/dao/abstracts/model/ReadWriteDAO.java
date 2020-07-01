package com.javamentor.qa.platform.dao.abstracts.model;

import java.util.List;

public interface ReadWriteDAO<T, PK> {

    void persist(T t);

    void update(T t);

    void delete(T t);

    void deleteByKeyCascadeEnable(PK id);

    void deleteByKeyCascadeIgnore(PK id);

    void deleteByFlagById(PK id);

    boolean existsById(PK id);

    T getByKey(PK id);

    List<T> getAll();

}
