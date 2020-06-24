package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("badges")
public class GetUserBadges implements Tabs {
    private final UserDtoDAO userDtoDAO;

    @Autowired
    public GetUserBadges(UserDtoDAO userDtoDAO) {
        this.userDtoDAO = userDtoDAO;
    }

    @Override
    public UserStatisticDto getList(String sortType, Long userId) {
        return UserStatisticDto.builder()
                .userBadges(userDtoDAO.getUserBadges(userId)).build();
    }
}
