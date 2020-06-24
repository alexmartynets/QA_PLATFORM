package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDAO;
import com.javamentor.qa.platform.dao.abstracts.model.UserBadgesDAO;
import com.javamentor.qa.platform.models.entity.user.Reputation;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.UserBadges;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReputationServiceImpl extends ReadWriteServiceImpl<Reputation, Long> implements ReputationService {

    private final ReputationDAO reputationDAO;
    private final UserBadgesDAO userBadgesDAO;

    @Autowired
    public ReputationServiceImpl(ReputationDAO reputationDAO,
                                 UserBadgesDAO userBadgesDAO) {
        super(reputationDAO);
        this.reputationDAO = reputationDAO;
        this.userBadgesDAO = userBadgesDAO;
    }

    @Override
    public void updateOrInsert(User user, int count) {
        Reputation reputation = Reputation.builder()
                .user(user)
                .count(count)
                .build();
        Optional<Reputation> optional = reputationDAO.findByUserIdAndDate(user);
        if (optional.isPresent()) {
            Reputation candidate = optional.get();
            candidate.setCount(candidate.getCount() + reputation.getCount());
            if (candidate.getCount() < 0) {
                candidate.setCount(0);
            }
            reputation.setId(candidate.getId());
            reputation.setCount(candidate.getCount());
            super.update(reputation);
        } else {
            super.persist(reputation);
        }
        if (count > 0) {
            checkAndUpdateUserBadges(reputation);
        }
    }

    private void checkAndUpdateUserBadges(Reputation reputation) {
        List<UserBadges> userBadges = userBadgesDAO.getUserBadges(reputation.getUser());
        if (userBadges != null) {
            userBadges.forEach(u -> {
                if (reputation.getCount() >= u.getBadges().getReputationForMerit()) {
                    u.setReady(true);
                    userBadgesDAO.update(u);
                }
            });
        }
    }
}
