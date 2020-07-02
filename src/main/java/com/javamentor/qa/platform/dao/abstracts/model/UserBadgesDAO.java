package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.UserBadges;

import java.util.List;

public interface UserBadgesDAO extends ReadWriteDAO<UserBadges, Long> {
    List<UserBadges> getUserBadges(User user);
}
