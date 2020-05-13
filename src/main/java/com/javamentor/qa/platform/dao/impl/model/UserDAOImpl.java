package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserDAO;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends ReadWriteDAOImpl<User, Long> implements UserDAO {
}
