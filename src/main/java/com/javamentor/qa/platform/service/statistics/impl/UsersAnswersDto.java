package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDAO;
import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("answer")
public class UsersAnswersDto implements Tabs {
    private final AnswerDtoDAO answerDtoDAO;

    @Autowired
    public UsersAnswersDto(AnswerDtoDAO answerDtoDAO) {
        this.answerDtoDAO = answerDtoDAO;
    }

    @Override
    public UserStatisticDto getList(String sortType, Long userId, Integer page) {
        List<AnswerDto> answerDto;
        UserStatisticDto userStatisticDto = UserStatisticDto.builder().build();
        if (sortType.equals("newest")) {
            answerDto = answerDtoDAO.getAnswerDtoByUserIdSortByDate(userId, page);
        } else if (sortType.equals("views")) {
            answerDto = answerDtoDAO.getAnswerDtoByUserIdSortByViews(userId, page);
        } else {
            answerDto = answerDtoDAO.getAnswerDtoByUserIdSortByVotes(userId, page);
        }
        userStatisticDto.setAnswerList(new Pair<>(answerDtoDAO.getAnswerCountByUserId(userId), answerDto));
        return userStatisticDto;
    }
}
