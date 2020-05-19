package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;

import java.util.List;
import java.util.Map;

public interface QuestionDtoService {
    List<QuestionDto> getAll();

    Map<Long, List<QuestionDto>> getPage(int page, int size);
}
