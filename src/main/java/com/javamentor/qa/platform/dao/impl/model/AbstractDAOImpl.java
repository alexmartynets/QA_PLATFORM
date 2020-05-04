package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracrt.model.AbstractDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
@Transactional
public abstract class AbstractDAOImpl<T, PK> implements AbstractDAO<T, PK> {

    protected Class<T> tClass;
//    private String tClassName;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractDAOImpl() {
        this.tClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
//        this.tClassName = tClass.getName().substring(tClass.getName().lastIndexOf(".") + 1);
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
        entityManager.remove(getByKey(id));
    }

    @Override
    public void deleteByKeyCascadeIgnore(PK id) {
        Query query = entityManager.createQuery(
                "DELETE FROM " + tClass.getName() + " u WHERE u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
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

/*@Service
class UserServiceImpl implements UserService{

   AbstractDAOImpl<User, Long> dao;

   @Autowired
   public void setDao(AbstractDAOImpl<User, Long> daoToSet) {
      dao = daoToSet;
      dao.setClazz(User.class);
   }

   // ...
}*/