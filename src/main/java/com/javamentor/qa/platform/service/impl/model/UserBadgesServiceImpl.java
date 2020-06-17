package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.dto.ReputationDtoDAO;
import com.javamentor.qa.platform.dao.abstracts.dto.UserBadgesDtoDAO;
import com.javamentor.qa.platform.dao.abstracts.model.UserBadgesDAO;
import com.javamentor.qa.platform.models.dto.UserBadgesDto;
import com.javamentor.qa.platform.models.entity.user.Reputation;
import com.javamentor.qa.platform.models.entity.user.UserBadges;
import com.javamentor.qa.platform.service.abstracts.model.UserBadgesService;
import com.javamentor.qa.platform.service.impl.util.CheckUserBadges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBadgesServiceImpl extends ReadWriteServiceImpl<UserBadges, Long> implements UserBadgesService {

    private final UserBadgesDAO userBadgesDAO;

    @Autowired
    public UserBadgesServiceImpl(UserBadgesDAO userBadgesDAO) {
        super(userBadgesDAO);
        this.userBadgesDAO = userBadgesDAO;
    }

    @Override
    public void checkAndUpdateUserBadges(Reputation reputation) {
        List<UserBadges> userBadges = userBadgesDAO.getUserBadges(reputation.getUser());
        if (userBadges != null) {
            for (UserBadges u : CheckUserBadges.checkBadges(reputation, userBadges)) {
                if (u.getReady()) {
                    userBadgesDAO.update(u);
                }
            }
        }
    }
}
