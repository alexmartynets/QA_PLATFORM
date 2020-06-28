package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserBadgesDAO;
import com.javamentor.qa.platform.models.entity.user.UserBadges;
import com.javamentor.qa.platform.service.abstracts.model.UserBadgesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBadgesServiceImpl extends ReadWriteServiceImpl<UserBadges, Long> implements UserBadgesService {

    private final UserBadgesDAO userBadgesDAO;

    @Autowired
    public UserBadgesServiceImpl(UserBadgesDAO userBadgesDAO) {
        super(userBadgesDAO);
        this.userBadgesDAO = userBadgesDAO;
    }
}

