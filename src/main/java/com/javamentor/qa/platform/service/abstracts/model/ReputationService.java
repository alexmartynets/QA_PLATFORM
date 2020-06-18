package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.Reputation;
import com.javamentor.qa.platform.models.entity.user.User;

public interface ReputationService extends ReadWriteService<Reputation, Long> {

    void updateOrInsert(User user, int count);
}
