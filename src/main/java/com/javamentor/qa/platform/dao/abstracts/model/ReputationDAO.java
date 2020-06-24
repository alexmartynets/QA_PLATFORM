package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.Reputation;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.Optional;

import java.util.List;

public interface ReputationDAO extends ReadWriteDAO<Reputation, Long> {
    Optional<Reputation> findByUserIdAndDate(User user);

    List<Reputation> getReputationByUserId(Long user_id);

    Long getSummOfUserReputation(Long user_id);
}
