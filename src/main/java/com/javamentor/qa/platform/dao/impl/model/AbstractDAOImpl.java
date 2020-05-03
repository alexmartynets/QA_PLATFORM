package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracrt.model.AbstractDAO;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public class AbstractDAOImpl<T, PK> implements AbstractDAO<T, PK> {

    private Class<T> tClass;

    @PersistenceContext
    private EntityManager entityManager;

    public AbstractDAOImpl() {
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

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("from " + tClass.getName()).getResultList();
    }
}
