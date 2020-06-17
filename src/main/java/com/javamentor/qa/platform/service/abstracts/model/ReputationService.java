package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.Reputation;

public interface ReputationService extends ReadWriteService<Reputation, Long> {

    Reputation updateOrInsert(Reputation reputation);
}
