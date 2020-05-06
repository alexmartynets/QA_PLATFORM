package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.abstracrt.dto.QuestionDaoDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracrt.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDaoDto questionDaoDto;

    @Override
    public List<QuestionDto> getAll() {
        return questionDaoDto.getListQuestionDto();
    }
}