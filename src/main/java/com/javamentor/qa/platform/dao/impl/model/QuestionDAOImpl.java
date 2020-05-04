package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracrt.model.QuestionDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public abstract class QuestionDAOImpl<T, PK> extends AbstractDAOImpl<T, PK> implements QuestionDAO<T, PK> {

}
