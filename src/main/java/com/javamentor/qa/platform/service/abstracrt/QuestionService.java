package com.javamentor.qa.platform.service.abstracrt;

import com.javamentor.qa.platform.models.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAll();
}
