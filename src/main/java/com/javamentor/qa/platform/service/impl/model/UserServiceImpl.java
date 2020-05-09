package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService {

    protected UserServiceImpl(ReadWriteDao<User, Long> readWriteDao) {
        super(readWriteDao);
    }
}