package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracrt.model.AbstractDAO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AbstractDAOImpl<T, PK> implements AbstractDAO<T, PK> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
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
        if(existsById(id)){
            entityManager.remove(getByKey(id));
        }
    }

    @Override
    public void deleteByKeyCascadeIgnore(PK id) {
        if(existsById(id)){
            entityManager.remove(getByKey(id));
        }
    }

    @Override
    public boolean existsById(PK id) {          //id or Key
        return entityManager.find(clazz, id) != null;
    }

    @Override
    public T getByKey(PK id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
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