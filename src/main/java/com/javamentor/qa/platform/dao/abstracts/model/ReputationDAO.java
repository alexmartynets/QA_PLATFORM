package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.Reputation;
import com.javamentor.qa.platform.models.entity.user.User;

public interface ReputationDAO extends ReadWriteDAO<Reputation, Long> {
    Reputation findByUserIdAndDate(User user);
}
