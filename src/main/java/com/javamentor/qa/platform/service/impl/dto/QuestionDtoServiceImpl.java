package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;

    @Autowired
    public QuestionDtoServiceImpl(QuestionDtoDao questionDtoDao) {
        this.questionDtoDao = questionDtoDao;
    }

    @Override
    public List<QuestionDto> getAll() {
        return questionDtoDao.getQuestionDtoList();
    }

    @Override
    public Map<Long, List<QuestionDto>> getPage(int page, int size) {
        return questionDtoDao.getPaginationQuestion(page, size);
    }
}