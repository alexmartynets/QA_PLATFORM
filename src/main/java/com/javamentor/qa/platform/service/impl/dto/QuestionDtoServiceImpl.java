package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    @Autowired
    private QuestionDtoDao questionDtoDao;

    @Override
    public List<QuestionDto> getAll() {
        return questionDtoDao.getQuestionDtoList();
    }

    @Override
    public Pair<Long, List<QuestionDto>> getPaginationQuestion(int page, int size) {
        List<QuestionDto> list = questionDtoDao.getQuestionList(page, size);
        list.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
        Pair<Long, List<QuestionDto>> result = new Pair<>(questionDtoDao.getCount(), list);
        return result;
    }
}