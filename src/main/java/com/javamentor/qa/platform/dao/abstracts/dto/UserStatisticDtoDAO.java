package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;

public interface UserStatisticDtoDAO {
    List<AnswerDto> getAnswerDto(User user);
}
