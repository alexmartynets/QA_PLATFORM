package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.abstracrt.model.AbstractDAO;
import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.entity.Role;
import com.javamentor.qa.platform.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServisTest {

    private AbstractDAOImpl<User, Long> dao = new AbstractDAOImpl<>();


    public void setDao(AbstractDAOImpl<User, Long> daoToSet) {
        dao = daoToSet;
        dao.setClazz(User.class);
    }

    public void addUser(){
        User user = new User();
        user.setEmail("email@email");
        user.setPassword("password");
        user.setRole(new Role("ADMIN"));
        user.setEnabled(true);
        dao.persist(user);
    }
}
