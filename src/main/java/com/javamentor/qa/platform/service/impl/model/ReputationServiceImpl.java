package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.dto.ReputationDtoDAO;
import com.javamentor.qa.platform.dao.abstracts.model.ReputationDAO;
import com.javamentor.qa.platform.models.dto.ReputationDto;
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

    @Override
    public Reputation updateOrInsert(Reputation reputation) {
        Reputation candidate = reputationDAO.findByUserIdAndDate(reputation.getUser());
        if (candidate != null) {
            candidate.setCount(candidate.getCount() + reputation.getCount());
            candidate.setVoiceCount(candidate.getVoiceCount() + 1);
            reputation.setId(candidate.getId());
            reputation.setCount(candidate.getCount());
            reputation.setVoiceCount(candidate.getVoiceCount());
            super.update(reputation);
        } else {
            super.persist(reputation);
        }
        return reputation;
    }
}
