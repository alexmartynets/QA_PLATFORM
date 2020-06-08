package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDAO;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Repository
public abstract class ReadWriteDAOImpl<T, PK> implements ReadWriteDAO<T, PK> {

    protected Class<T> tClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public ReadWriteDAOImpl() {
        this.tClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public void persist(T t) {
        entityManager.persist(t);
    }

    @Override
    @Transactional
    public void update(T t) {
        entityManager.merge(t);
    }

    @Override
    @Transactional
    public void delete(T t) {
        entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
    }

    @Override
    @Transactional
    public void deleteByKeyCascadeEnable(PK id) {
        entityManager.remove(entityManager.find(tClass, id));
    }

    @Override
    @Transactional
    public void deleteByKeyCascadeIgnore(PK id) {
        Query query = entityManager.createQuery(
                "DELETE FROM " + tClass.getName() + " u WHERE u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteByFlagById(PK id) {
        if (tClass.isAnnotationPresent(Where.class)) {
            entityManager.createQuery(
                    "UPDATE " + tClass.getName() + " e SET e.isDeleted = TRUE WHERE e.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } else {
            throw new RuntimeException("В классе нет признака удаление по флагу");
        }
    }

    @Override
    public boolean existsById(PK id) {
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
