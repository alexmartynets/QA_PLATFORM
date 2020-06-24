package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.User;

import java.util.Optional;

public interface ReputationDAO extends ReadWriteDAO<Reputation, Long> {
    Optional<Reputation> findByUserIdAndDate(User user);
}
