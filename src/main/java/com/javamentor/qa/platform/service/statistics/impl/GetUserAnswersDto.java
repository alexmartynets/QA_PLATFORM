package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDAO;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("answer")
public class GetUserAnswersDto implements Tabs {
    private final AnswerDtoDAO answerDtoDAO;

    @Autowired
    public GetUserAnswersDto(AnswerDtoDAO answerDtoDAO) {
        this.answerDtoDAO = answerDtoDAO;
    }

    @Override
    public UserStatisticDto getList(String sortType, Long userId) {
        UserStatisticDto userStatisticDto = UserStatisticDto.builder().build();
        if (sortType.equals("newest")) {
            userStatisticDto.setAnswerList(answerDtoDAO.getAnswerDtoByUserIdSortByDate(userId));
        } else if (sortType.equals("views")) {
            userStatisticDto.setAnswerList(answerDtoDAO.getAnswerDtoByUserIdSortByViews(userId));
        } else {
            userStatisticDto.setAnswerList(answerDtoDAO.getAnswerDtoByUserIdSortByVotes(userId));
        }
        return userStatisticDto;
    }
}
