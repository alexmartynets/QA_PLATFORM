package com.javamentor.qa.platform.dao.abstracrt.dto;

import com.javamentor.qa.platform.models.dto.AnswerDto;

import java.util.List;

public interface DtoAnswerDAO {
    List<AnswerDto> getAnswersDto(Long questionId);
}
