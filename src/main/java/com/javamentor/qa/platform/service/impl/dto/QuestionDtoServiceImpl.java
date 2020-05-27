package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    @Autowired
    private QuestionDtoDao questionDtoDao;

    @Override
    public List<QuestionDto> getAll() {
        return questionDtoDao.getQuestionDtoList();
    }

    @Override
    public Map<Long, List<QuestionDto>> getPage(int page, int size) {
        Map<Long, List<QuestionDto>> result = new HashMap<>();
        List<QuestionDto> list = questionDtoDao.getPaginationQuestion(page, size);
        list.forEach(f -> f.setTags(questionDtoDao.getTag(f.getId())));
        result.put(questionDtoDao.getCount(), list);
        return result;
    }
}