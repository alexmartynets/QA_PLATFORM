package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ReadWriteDaoImpl<User, Long> {
}