package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.service.abstracts.model.ReadWriteService;

import java.util.List;

public class ReadWriteServiceImpl<T, PK> implements ReadWriteService<T, PK> {
    @Override
    public void persist(T t) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void deleteByKeyCascadeEnable(PK id) {

    }

    @Override
    public void deleteByKeyCascadeIgnore(PK id) {

    }

    @Override
    public boolean existsById(PK id) {
        return false;
    }

    @Override
    public T getByKey(PK id) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }
}
