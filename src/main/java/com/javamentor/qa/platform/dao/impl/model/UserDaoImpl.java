package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracrt.model.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl<T, PK> extends AbstractDAOImpl<T, PK> implements UserDao<T, PK> {
}