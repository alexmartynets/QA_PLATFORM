package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractDAOImpl<User, Long> {
}