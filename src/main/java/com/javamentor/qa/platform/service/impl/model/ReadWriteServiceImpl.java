package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.service.abstracts.model.ReadWriteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public abstract class ReadWriteServiceImpl<T, PK> implements ReadWriteService<T, PK> {


    private final ReadWriteDao<T, PK> readWriteDao;

    protected ReadWriteServiceImpl(ReadWriteDao<T, PK> readWriteDao) {
        this.readWriteDao = readWriteDao;
    }

    @Override
    @Transactional
    public void persist(T t) {
        readWriteDao.persist(t);
    }

    @Override
    @Transactional
    public void update(T t) {
        readWriteDao.update(t);
    }

    @Override
    @Transactional
    public void delete(T t) {
        readWriteDao.delete(t);
    }

    @Override
    @Transactional
    public void deleteByKeyCascadeEnable(PK id) {
        readWriteDao.deleteByKeyCascadeEnable(id);
    }

    @Override
    @Transactional
    public void deleteByKeyCascadeIgnore(PK id) {
        readWriteDao.deleteByKeyCascadeIgnore(id);
    }

    @Override
    public boolean existsById(PK id) {
        return readWriteDao.existsById(id);
    }

    @Override
    public T getByKey(PK id) {
        return readWriteDao.getByKey(id);
    }

    @Override
    public List<T> getAll() {
        return readWriteDao.getAll();
    }
}
