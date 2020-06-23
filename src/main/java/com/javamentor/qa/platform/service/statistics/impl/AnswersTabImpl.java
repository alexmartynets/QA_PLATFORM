package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDAO;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tab;
import org.springframework.beans.factory.annotation.Autowired;

public class AnswersTabImpl implements Tab {
    private UserStatisticDto userStatisticDto;
    private AnswerDtoDAO answerDtoDAO;

    @Autowired
    public AnswersTabImpl(UserStatisticDto userStatisticDto, AnswerDtoDAO answerDtoDAO) {
        this.userStatisticDto = userStatisticDto;
        this.answerDtoDAO = answerDtoDAO;
    }

    @Override
    public UserStatisticDto getStatistics(Long user_id, String sort, int page) {
        return UserStatisticDto.builder()
                .answerList(answerDtoDAO.getAnswerDtoByUserId(user_id, sort, page))
                .build();
    }
}
