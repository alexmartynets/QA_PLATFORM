package com.javamentor.qa.platform.service.impl;


import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServisTest {


    private AbstractDAOImpl<User, Long> dao;

    @Autowired
    public void setDao(AbstractDAOImpl<User, Long> daoToSet) {
        dao = daoToSet;
        dao.setClazz(User.class);
    }

    public void addUser(){
        User user = new User();
        user.setEmail("email@email");
        user.setPassword("password");
        user.setRole(new Role("ADMIN"));
        user.setIsEnabled(true);
        dao.persist(user);
    }
}
