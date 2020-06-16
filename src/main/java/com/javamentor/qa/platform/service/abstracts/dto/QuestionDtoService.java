package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface QuestionDtoService {
    List<QuestionDto> getAll();

    Pair<Long, List<QuestionDto>> getPaginationQuestion(int page, int size);
}
