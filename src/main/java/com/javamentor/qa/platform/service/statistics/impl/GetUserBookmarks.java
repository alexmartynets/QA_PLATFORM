package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.model.UserFavoriteQuestionDAO;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bookmarks")
public class GetUserBookmarks implements Tabs {
    private final UserFavoriteQuestionDAO userFavoriteQuestionDAO;

    @Autowired
    public GetUserBookmarks(UserFavoriteQuestionDAO userFavoriteQuestionDAO) {
        this.userFavoriteQuestionDAO = userFavoriteQuestionDAO;
    }

    @Override
    public UserStatisticDto getList(String sortType, Long userId) {
        return UserStatisticDto.builder()
                .userFavoriteQuestions(userFavoriteQuestionDAO.getUserFavorite(userId))
                .build();
    }
}
