package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDAO;
import com.javamentor.qa.platform.models.entity.user.Reputation;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReputationServiceImpl extends ReadWriteServiceImpl<Reputation, Long> implements ReputationService {

    private final ReputationDAO reputationDAO;

    @Autowired
    public ReputationServiceImpl(ReputationDAO reputationDAO) {
        super(reputationDAO);
        this.reputationDAO = reputationDAO;
    }


}

