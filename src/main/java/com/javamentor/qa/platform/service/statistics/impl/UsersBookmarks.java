package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.model.UserFavoriteQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bookmarks")
public class UsersBookmarks implements Tabs {
    private final UserFavoriteQuestionDAO userFavoriteQuestionDAO;
    private final QuestionDtoDao questionDtoDao;

    @Autowired
    public UsersBookmarks(UserFavoriteQuestionDAO userFavoriteQuestionDAO,
                          QuestionDtoDao questionDtoDao) {
        this.userFavoriteQuestionDAO = userFavoriteQuestionDAO;
        this.questionDtoDao = questionDtoDao;
    }

    @Override
    public UserStatisticDto getList(String sortType, Long userId, Integer page) {
        List<QuestionDto> questionDto;
        if (sortType.equals("newest")) {
            questionDto = userFavoriteQuestionDAO.getUserFavoriteSortByDate(userId, page);
        }else if (sortType.equals("views")) {
            questionDto = userFavoriteQuestionDAO.getUserFavoriteSortByViews(userId, page);
        } else {
            questionDto = userFavoriteQuestionDAO.getUserFavoriteSortByVotes(userId, page);
        }
        questionDto.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
        return UserStatisticDto.builder()
                .userFavoriteQuestions(new Pair<>(userFavoriteQuestionDAO.getCountOfUserFavoriteQuestion(userId), questionDto))
                .build();
    }
}
