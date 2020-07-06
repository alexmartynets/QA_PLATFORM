package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("badges")
public class UsersBadges implements Tabs {
    private final UserDtoDAO userDtoDAO;

    @Autowired
    public UsersBadges(UserDtoDAO userDtoDAO) {
        this.userDtoDAO = userDtoDAO;
    }

    @Override
    public UserStatisticDto getList(String sortType, Long userId, Integer page) {
        return UserStatisticDto.builder()
                .userBadges(new Pair<>(userDtoDAO.getCountOfUserBadges(userId), userDtoDAO.getUserBadges(userId, page)))
                .build();
    }
}
