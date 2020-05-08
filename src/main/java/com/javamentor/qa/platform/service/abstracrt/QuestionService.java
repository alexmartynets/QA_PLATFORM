package com.javamentor.qa.platform.service.abstracrt;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAll();
}
