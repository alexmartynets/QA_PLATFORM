package com.javamentor.qa.platform.service.impl.util;

import com.javamentor.qa.platform.models.entity.user.Reputation;
import com.javamentor.qa.platform.models.entity.user.UserBadges;

import java.util.List;

public class CheckUserBadges {

    public static List<UserBadges> checkBadges(Reputation reputation, List<UserBadges> list) {
        list.forEach(u -> {
            if(!u.getReady()) {
                if (reputation.getCount() >= u.getBadges().getCount()) {
                    u.setReady(true);
                    u.setCountOfBadges(u.getCountOfBadges() + 1);
                }
            }
        });
        return list;
    }
}
