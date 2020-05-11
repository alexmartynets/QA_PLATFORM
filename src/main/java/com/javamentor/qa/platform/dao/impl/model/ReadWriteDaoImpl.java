package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
@Transactional
public abstract class ReadWriteDaoImpl<T, PK> implements ReadWriteDao<T, PK> {

    private Class<T> tClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public ReadWriteDaoImpl() {
        this.tClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public void persist(T t) {
        entityManager.persist(t);
    }

    @Override
    public void update(T t) {
        entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }

    @Override
    public void deleteByKeyCascadeEnable(PK id) {
        if (existsById(id)) {
            entityManager.remove(getByKey(id));
        }
    }

    @Override
    public void deleteByKeyCascadeIgnore(PK id) {
        if (existsById(id)) {
            Query query = entityManager.createQuery("DELETE FROM " + tClass.getName() + " u WHERE u.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        }
    }

    @Override
    public boolean existsById(PK id) {          //id or Key
        return entityManager.find(tClass, id) != null;
    }

    @Override
    public T getByKey(PK id) {
        return entityManager.find(tClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return entityManager.createQuery("from " + tClass.getName()).getResultList();
    }
}
