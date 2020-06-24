package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("question")
public class GetUserQuestionDto implements Tabs {
    private final QuestionDtoDao questionDtoDao;

    @Autowired
    public GetUserQuestionDto(QuestionDtoDao questionDtoDao) {
        this.questionDtoDao = questionDtoDao;
    }

    @Override
    public UserStatisticDto getList(String sortType, Long userId) {
        UserStatisticDto userStatisticDto = UserStatisticDto.builder().build();
        if (sortType.equals("newest")) {
            userStatisticDto.setQuestionDtoList(questionDtoDao.getQuestionDtoByUserIdSortByDate(userId));
        } else if (sortType.equals("views")) {
            userStatisticDto.setQuestionDtoList(questionDtoDao.getQuestionDtoByUserIdSortByViews(userId));
        } else {
            userStatisticDto.setQuestionDtoList(questionDtoDao.getQuestionDtoByUserIdSortByVotes(userId));
        }
        return userStatisticDto;
    }
}
