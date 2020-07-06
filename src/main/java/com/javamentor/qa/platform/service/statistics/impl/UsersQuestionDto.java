package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("question")
public class UsersQuestionDto implements Tabs {
    private final QuestionDtoDao questionDtoDao;

    @Autowired
    public UsersQuestionDto(QuestionDtoDao questionDtoDao) {
        this.questionDtoDao = questionDtoDao;
    }

    @Override
    public UserStatisticDto getList(String sortType, Long userId, Integer page) {
        UserStatisticDto userStatisticDto = UserStatisticDto.builder().build();
        List<QuestionDto> questionDto;

        if (sortType.equals("newest")) {
            questionDto = questionDtoDao.getQuestionDtoByUserIdSortByDate(userId, page);
        } else if (sortType.equals("views")) {
            questionDto = questionDtoDao.getQuestionDtoByUserIdSortByViews(userId, page);
        } else {
            questionDto = questionDtoDao.getQuestionDtoByUserIdSortByVotes(userId, page);
        }

        questionDto.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
        userStatisticDto.setQuestionDtoList(new Pair<>(questionDtoDao.getQuestionCountByUserId(userId), questionDto));
        return userStatisticDto;
    }
}
